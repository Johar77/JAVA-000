package com.johar.geektime.dubbohmilytccdemo.bussiness;

import com.johar.geektime.dubbohmilytccdemo.annotation.DynamicSource;
import com.johar.geektime.dubbohmilytccapi.AccountType;
import com.johar.geektime.dubbohmilytccdemo.config.DataSourceConfiguration;
import com.johar.geektime.dubbohmilytccdemo.entity.AccountPO;
import com.johar.geektime.dubbohmilytccdemo.entity.FrozenPO;
import com.johar.geektime.dubbohmilytccdemo.entity.TransactionPO;
import com.johar.geektime.dubbohmilytccdemo.repository.AccountRepository;
import com.johar.geektime.dubbohmilytccdemo.repository.FrozenRepository;
import com.johar.geektime.dubbohmilytccdemo.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: TransferBussiness
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/22 11:46
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class TransferBusiness {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FrozenRepository frozenRepository;

    @Transactional(rollbackFor = Exception.class)
    @DynamicSource(name = DataSourceConfiguration.FIRST)
    public void transferA(){
        List<AccountPO> accountPOList = accountRepository.findAllByUserId(1L);
        transfer(accountPOList.get(0).getAccountType(), 100L, accountPOList.get(1).getAccountType());
    }

    @Transactional(rollbackFor = Exception.class)
    @DynamicSource(name = DataSourceConfiguration.FIRST)
    public boolean confirmA(){
        TransactionPO transactionPO = transactionRepository.findFirstByUserId(1L);
        return confirm(transactionPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @DynamicSource(name = DataSourceConfiguration.FIRST)
    public boolean cancelA(){
        TransactionPO transactionPO = transactionRepository.findFirstByUserId(1L);
        return cancel(transactionPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @DynamicSource(name = DataSourceConfiguration.SECOND)
    public void transferB(){
        List<AccountPO> accountPOList = accountRepository.findAllByUserId(1001L);
        transfer(AccountType.CNY, 100L, AccountType.USD);
    }

    @Transactional(rollbackFor = Exception.class)
    @DynamicSource(name = DataSourceConfiguration.SECOND)
    public boolean confirmB(){
        TransactionPO transactionPO = transactionRepository.findFirstByUserId(1001L);
        return confirm(transactionPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @DynamicSource(name = DataSourceConfiguration.SECOND)
    public boolean cancelB(){
        TransactionPO transactionPO = transactionRepository.findFirstByUserId(1001L);
        return cancel(transactionPO);
    }


    /**
     * 个人账户之间转账
     * @param fromAccountType
     * @param amount
     * @param toAccountType
     */
    private void transfer(Integer fromAccountType, long amount, Integer toAccountType){
        try {
            long userId = 1L;
            AccountPO fromAccount = accountRepository.findAccountPOByUserIdAndAccountType(userId, fromAccountType);
            double rate = 1;
            rate = getRate(toAccountType, AccountType.USD);
            long totalMoney = (long) (amount * rate);
            if (totalMoney > fromAccount.getAvailableAmount()) {
                throw new IllegalArgumentException("account don't have enough money");
            }
            AccountPO toAccount = accountRepository.findAccountPOByUserIdAndAccountType(userId, toAccountType);

            TransactionPO transactionPO = new TransactionPO();
            transactionPO.setFromAccountId(fromAccount.getId());
            transactionPO.setToAccountId(toAccount.getId());
            transactionPO.setUserId(userId);
            transactionPO.setAmount(amount);
            transactionPO.setAccountType(toAccountType);
            transactionRepository.save(transactionPO);

            FrozenPO frozenPO = new FrozenPO();
            frozenPO.setAccountId(fromAccount.getId());
            frozenPO.setAmount(amount);
            frozenPO.setAccountType(toAccountType);
            frozenPO.setTransactionId(transactionPO.getId());
            frozenRepository.save(frozenPO);

            fromAccount.setFreezeAmount(fromAccount.getFreezeAmount() + totalMoney);
            fromAccount.setAvailableAmount(fromAccount.getAvailableAmount() - totalMoney);
            accountRepository.save(fromAccount);
        } catch (Exception e){
            log.error("error: ", e);
        }

    }

    private double getRate(Integer toAccountType, int usd) {
        double rate;
        if (usd == toAccountType) {
            rate = getUsdExchangeRate();
        } else {
            rate = getCnyExchangeRate();
        }
        return rate;
    }

    private boolean confirm(TransactionPO transactionPO){
        if (transactionPO == null){
            return true;
        }
        Optional<AccountPO> fromAccount = accountRepository.findById(transactionPO.getFromAccountId());
        Optional<AccountPO> toAccount = accountRepository.findById(transactionPO.getToAccountId());
        double rate = getRate(AccountType.USD, transactionPO.getAccountType());
        long totalMoney = (long)(transactionPO.getAmount() * rate);
        // 减去支付账户钱
        fromAccount.ifPresent(accountPO -> {
            accountPO.setFreezeAmount(accountPO.getFreezeAmount() - totalMoney);
            accountPO.setTotalAmount(accountPO.getTotalAmount() - totalMoney);
            accountRepository.save(accountPO);
        });
        // 删除冻结记录
        frozenRepository.deleteAllByTransactionId(transactionPO.getId());
        // 增加收款账户钱
        toAccount.ifPresent(accountPO -> {
            accountPO.setAvailableAmount(accountPO.getAvailableAmount() + transactionPO.getAmount());
            accountPO.setTotalAmount(accountPO.getTotalAmount() + transactionPO.getAmount());
            accountRepository.save(accountPO);
        });

        return true;
    }

    private boolean cancel(TransactionPO transactionPO){
        if (transactionPO == null){
            return true;
        }
        Optional<AccountPO> fromAccount = accountRepository.findById(transactionPO.getFromAccountId());
        double rate = getRate(AccountType.USD, transactionPO.getAccountType());
        long totalMoney = (long)(transactionPO.getAmount() * rate);
        // 增加支付账户钱
        fromAccount.ifPresent(accountPO -> {
            accountPO.setFreezeAmount(accountPO.getFreezeAmount() - totalMoney);
            accountPO.setAvailableAmount(accountPO.getAvailableAmount() + totalMoney);
            accountRepository.save(accountPO);
        });
        // 删除冻结记录
        frozenRepository.deleteAllByTransactionId(transactionPO.getId());

        return true;
    }


    private double getUsdExchangeRate(){
        return 6.55;
    }

    private double getCnyExchangeRate(){
        return 0.15;
    }
}