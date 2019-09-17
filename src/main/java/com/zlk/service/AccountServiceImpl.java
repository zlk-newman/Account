package com.zlk.service;
import com.zlk.dao.AccountDao;
import org.springframework.stereotype.Service;
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class AccountServiceImpl implements AccountService {
    //响应xml中service调控Dao
    private AccountDao ADao;
    public AccountDao getADao() {
        return ADao;
    }
    public void setADao(AccountDao ADao) {
        this.ADao = ADao;
    }
    //重写转账方法
    @Override
    public void Account(int nid,int id,double b){
        double n=ADao.getUBalance(id)-b;
        ADao.getOBalance(id,n);
        double d=ADao.getUBalance(nid)+b;
        ADao.getOBalance(nid,d);
    }


}
