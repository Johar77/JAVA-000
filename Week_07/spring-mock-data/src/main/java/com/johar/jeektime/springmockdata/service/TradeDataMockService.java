package com.johar.jeektime.springmockdata.service;

import com.johar.jeektime.springmockdata.domain.Product;
import com.johar.jeektime.springmockdata.jdbc.BatchPrepareStatementJdbcRepository;
import com.johar.jeektime.springmockdata.jdbc.PreparedStatementJdbcRepository;
import com.johar.jeektime.springmockdata.utils.RandString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;


/**
 * @ClassName: TradeDataMockService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/2 23:04
 * @Since: 1.0.0
 */
@Service
@Order(3)
public class TradeDataMockService implements CommandLineRunner {

    private final String order_master_sql = "insert into t_order_master(order_sn,customer_id,user_address_id,payment_method,"
            + "order_money,district_money,shipping_company_name, shipping_sn, create_time,shipping_time,pay_time,receive_time,"
            +"order_status,order_point,invoice_header,update_time,escrow_trade_no,payment_account_id,shroff_account_number,payment_money)"
            +" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    private final String order_detail_sql = "insert into t_order_detail(order_id,product_id,product_name,product_count,product_price,"
            +"weight,fee_money) values(?,?,?,?,?,?,?);";

    private final String max_user_id = "select max(user_id) as max_user_id from t_user_info tui;";
    private final String max_product_id = "select max(product_id) as max_product_id from t_product_info tpi;";

    private final String product_sql = "select product_id,product_name,price,weight from t_product_info where product_id = ?;";
    private final String query_address_id_sql = "select address_id from t_user_address where user_id = ?";
    private final String query_trade_id = "select order_id from t_order_master where order_sn = ?;";

    @Autowired
    private PreparedStatementJdbcRepository repository;

    @Autowired
    private BatchPrepareStatementJdbcRepository batchRepository;

    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            10 * Runtime.getRuntime().availableProcessors(),
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000_000));
    private ConcurrentHashMap<Integer, Product> productMap;

    @Override
    public void run(String... args) throws Exception {
        //multiThread();

        // 数据库中的id是连续的，所以随机生成customer_id,product_id
        List<Map<String, Object>> maxUserIdList = repository.query(max_user_id);
        BigInteger maxUserId = (BigInteger)maxUserIdList.get(0).get("max_user_id");
        List<Map<String, Object>> maxProductIdList = repository.query(max_product_id);
        BigInteger maxProductId = (BigInteger)maxProductIdList.get(0).get("max_product_id");
        productMap = new ConcurrentHashMap<Integer, Product>(maxProductId.intValue());
        int num = 1000;
        Random random = new Random();
        for (int i = 0; i < num; i++){
            executorService.submit(() ->{
                try {
                    List<Object[]> orderMasterParams = new ArrayList<>(num);
                    HashMap<String, BigInteger> orderDetailParams = new HashMap<>(num);
                    for (int j = 0; j < num; j++) {
                        int productId = random.nextInt(maxProductId.intValue());
                        if (productId == 0) {
                            productId = 1;
                        }
                        int userId = random.nextInt(maxUserId.intValue());
                        if (userId == 0) {
                            userId = 1;
                        }
                        Product product = getProduct(productId);
                        String tradeSN = RandString.randNum(18);
                        orderMasterParams.add(getOrderMasterParams(tradeSN, userId, product));
                        orderDetailParams.put(tradeSN, product.getProduct_id());
                    }
                    batchRepository.batchUpdate(order_master_sql, orderMasterParams);

                    List<Object[]> params = new ArrayList<>(num);
                    for (Map.Entry<String, BigInteger> entry : orderDetailParams.entrySet()) {
                        List<Map<String, Object>> mapList = repository.query(query_trade_id, new Object[]{entry.getKey()});
                        //ids.put((BigInteger) mapList.get(0).get("order_id"), entry.getValue());
                        params.add(getOrderDetailParams(getProduct(entry.getValue().intValue()), (BigInteger) mapList.get(0).get("order_id")));
                    }
                    batchRepository.batchUpdate(order_detail_sql, params);
                } catch (Exception e){
                    System.out.println(e);
                }
            });
        }
        executorService.shutdown();
    }

    private Product getProduct(int productId){
        if (productMap.containsKey(productId)){
            return productMap.get(productId);
        }

        Product product = null;
        try {
            product = repository.findOne(product_sql, new Object[]{productId}, Product.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        productMap.put(productId, product);
        return product;
    }

    private void multiThread() {
        // 数据库中的id是连续的，所以随机生成customer_id,product_id
        List<Map<String, Object>> maxUserIdList = repository.query(max_user_id);
        BigInteger maxUserId = (BigInteger)maxUserIdList.get(0).get("max_user_id");
        List<Map<String, Object>> maxProductIdList = repository.query(max_product_id);
        BigInteger maxProductId = (BigInteger)maxProductIdList.get(0).get("max_product_id");
        int num = 1000_000;
        Random random = new Random();
        for (int i = 0; i < num; i++){
            executorService.submit(() ->
            {
                int productId = random.nextInt(maxProductId.intValue());
                if (productId == 0){
                    productId = 1;
                }
                int userId = random.nextInt(maxUserId.intValue());
                if (userId == 0){
                    userId = 1;
                }
                    Product product = null;
                    try {
                        product = repository.findOne(product_sql, new Object[] {productId}, Product.class);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    String tradeSN = RandString.randNum(18);
                repository.update(order_master_sql, getOrderMasterParams(tradeSN, userId, product));
                List<Map<String,Object>> mapList = repository.query(query_trade_id, new Object[]{tradeSN});
                repository.update(order_detail_sql, getOrderDetailParams(product, (BigInteger) mapList.get(0).get("order_id")));
            });
        }
        executorService.shutdown();
    }

    private Object[] getOrderDetailParams(Product product, BigInteger orderId){
        List<Object> params = new ArrayList<>();
        params.add(orderId);
        params.add(product.getProduct_id());
        params.add(product.getProduct_name());
        params.add(1);
        params.add(product.getPrice());
        params.add(product.getWeight());
        params.add(0L);

        return params.toArray();
    }

    private Object[] getOrderMasterParams(String sn, int userId, Product product){
        List<Object> params = new ArrayList<>();
        params.add(sn);
        params.add(userId);
        List<Map<String,Object>> addressInfo = repository.query(query_address_id_sql, new Object[]{userId});
        if (addressInfo == null || addressInfo.size() == 0){
            return null;
        }
        params.add(addressInfo.get(0).get("address_id"));
        Random random = new Random(System.currentTimeMillis());
        params.add(random.nextInt(4));
        params.add(product.getPrice());
        params.add(0L);
        params.add(RandString.randCharter(6));
        params.add(RandString.randNum(16));
        params.add(LocalDateTime.now());
        params.add(LocalDateTime.now());
        params.add(LocalDateTime.now());
        params.add(LocalDateTime.now());
        params.add(random.nextInt(6));
        params.add(random.nextInt(100));
        params.add(RandString.randCharter(12));
        params.add(LocalDateTime.now());
        params.add(RandString.randNum(12));
        params.add(RandString.randNum(12));
        params.add(RandString.randNum(12));
        params.add(product.getPrice());

        return params.toArray();
    }
}