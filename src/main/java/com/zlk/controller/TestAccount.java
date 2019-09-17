package com.zlk.controller;

import com.zlk.bean.Account;
import com.zlk.dao.AccountDao;
import com.zlk.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.util.List;
import java.util.Scanner;

/*
 * RunWith注解表示在测试环境中，初始化Spring容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * 加载Spring的配置文件，并初始化Spring上下文
 * */
@ContextConfiguration("classpath:configs/config-spring.xml")
public class TestAccount {
    //配置测试类要用到的内部属性
    private int id=0;//获取当前账户id
    private String uname=null;//获取当前账户用户名
    private String a=null;//用于判断（y/n）
    private double b=0.0;//获取要转账的金额
    private int nid=0;//获取转入id

    // 方法1：使用注解的方式，进行依赖注入
    // 使用Autowired注解，进行依赖注入（反转控制）
    @Autowired
    ApplicationContext ctx;
    @Autowired
    AccountDao Dao;
    @Autowired
    AccountService service;

//    //方法2：配置Before注解下的方法，每次Test前优先运行Before注解下的方法。
//    private ApplicationContext ctx = null;
//    private AccountDao Dao = null;
//    @Before
//    public void setUp() throws Exception {
//        ctx = new ClassPathXmlApplicationContext("classpath:configs/config-spring.xml");
//        Dao = (AccountDao) ctx.getBean("AccountDao");
//    }
    //配置主测试方法
    @Test
    public void test(){
        Dao=(AccountDao)ctx.getBean("AccountDao");
        service=(AccountService)ctx.getBean("AccountService");
            //此处有死锁问题，需用同步锁来控制Scanner流与当前test测试线程同步
            synchronized (this) {
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入您的账户id:");
                id = sc.nextInt();
            }
            System.out.println("您输入的账户为："+id);
            List<Account> list = Dao.getAccount(id);
            if (list != null && list.size() > 0) {
                uname=Dao.getName(id);
                System.out.println("尊敬的用户："+uname+",您好！");
                System.out.println("您的账户信息为："+list.toString());
                CAccount();
            } else
            {
                System.out.println("此账户不存在，请确认后重新输入！");
                test();
            }

    }
    //内部方法1：用于判断用户是否选择转账
    @Test
    public void CAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("是否选择转账（y/n）：");
        a= sc.nextLine();
        if (a.equals("y")){
            System.out.println("下面为您转账:");
            OutAccount();
        }else if (a.equals("n")){
            System.out.println("很高兴为您服务，再见！");
        }else {
            System.out.println("您的输入有误，请重新输入！");
            CAccount();
        }
    }
    //内部方法2：用于判断余额是否充足
    @Test
    public void OutAccount() {
        Dao=(AccountDao)ctx.getBean("AccountDao");
        service=(AccountService)ctx.getBean("AccountService");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入转账金额：");
        b = sc.nextDouble();
        double c = Dao.getUBalance(id);
        if (b <= c) {
            InAccount();
        } else {
            System.out.println("余额不足，请重新确定是否转账。");
            CAccount();
        }
    }
    //内部方法3：判断转入账户是否存在并调用service内的转账方法
    @Test
    public void InAccount(){
        Dao=(AccountDao)ctx.getBean("AccountDao");
        service=(AccountService)ctx.getBean("AccountService");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入转入账户：");
        nid=sc.nextInt();
        List<Account> list = Dao.getAccount(nid);
        if (list != null && list.size() > 0) {
            //掉用service类内的转账方法
            service.Account(nid, id, b);
            System.out.println("转账完成！");
            System.out.println("您的账户信息如下：");
            List<Account> list1 = Dao.getAccount(id);
            System.out.println(list1);
            CAccount();
        } else
        {
            System.out.println("该账户不存在，请确认后重新输入！");
            InAccount();
        }
    }

}
