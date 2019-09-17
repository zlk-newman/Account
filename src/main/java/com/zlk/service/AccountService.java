package com.zlk.service;

import com.zlk.dao.AccountDao;
import org.springframework.context.ApplicationContext;

public interface AccountService {

    //配置转账方法
    public void Account(int nid,int id,double b);
}
