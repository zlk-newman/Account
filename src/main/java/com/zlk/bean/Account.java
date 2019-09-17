package com.zlk.bean;

public class Account {
    private int id;//用户账号
    private String uname;//用户名
    private double balance;//余额

    //无参构造
    public Account() {
    }
    //有参构造
    public Account(int id, String uname, double balance) {
        this.id = id;
        this.uname = uname;
        this.balance = balance;
    }
    //配置toString输出格式
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", balance=" + balance +
                '}';
    }
//配置各个getter和setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
