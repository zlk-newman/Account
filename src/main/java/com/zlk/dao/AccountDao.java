package com.zlk.dao;
import com.zlk.bean.Account;
import java.util.List;
public interface AccountDao {
    //配置根据id查询一条数据的方法
    public List<Account> getAccount(int id);
    //配置根据id查询用户名的方法
    public String getName(int id);
    //配置根据id查询余额的方法
    public double getUBalance(int id);
    //配置修改余额的方法
    public void getOBalance(int id, double money);
}
