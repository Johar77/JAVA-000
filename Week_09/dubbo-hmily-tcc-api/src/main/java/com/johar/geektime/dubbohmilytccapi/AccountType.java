package com.johar.geektime.dubbohmilytccapi;

/**
 * @ClassName: AccountType
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 18:24
 * @Since: 1.0.0
 */
public enum AccountType {

    /**
     * 人民币
     */
    CNY(1),

    /**
     * 美元
     */
    USD(2);

    private int type;

    AccountType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
