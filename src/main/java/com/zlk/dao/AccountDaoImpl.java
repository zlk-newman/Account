package com.zlk.dao;

import com.zlk.bean.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AccountDaoImpl implements AccountDao {
    //响应xml中Dao调控jdbcTemplate
    private JdbcTemplate template;
    public JdbcTemplate getTemplate() {
        return template;
    }
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    //重写接口内根据id查询一条记录的方法
    @Override
    public List<Account> getAccount(int id) {
        String sql = "select * from account where id="+id+"";
        return (List<Account>) template.query(sql, new BeanPropertyRowMapper(Account.class));
    }
    //重写接口内根据id查询名字的方法
    @Override
    public String getName(int id) {
        String uname = new String();
        String sql = "select uname from account where id="+id+"";
        List<Account> list=(List<Account>)template.query(sql, new BeanPropertyRowMapper(Account.class));
        for (Account account:list){
            uname=account.getUname();
        }
        return uname;
    }
    //重写根据id查询余额的方法
    @Override
    public double getUBalance(int id){
        double balance=0.0;
        String sql = "select balance from account where id="+id+"";
        List<Account> list=(List<Account>)template.query(sql, new BeanPropertyRowMapper(Account.class));
        for (Account account:list){
            balance=account.getBalance();
        }
        return balance;
    }
    //重写修改余额的方法
    @Override
    public void getOBalance(int id,double money) {
        String sql = "update account set balance = ? where id = ?";
        template.update(sql, money, id);
    }
}
