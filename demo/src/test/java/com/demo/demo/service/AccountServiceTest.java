package com.demo.demo.service;

import com.demo.DemoApplicationTests;
import com.demo.demo.domain.AccountDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import self.yang.mybatis.domain.PageModel;

/**
 * com.demo.demo.service.AccountServiceTest
 *
 * @author eleven
 * @date 2019/09/21
 */
public class AccountServiceTest extends DemoApplicationTests {

    @Autowired
    private AccountMybatisService accountService;

    @Test
    public void test() {
        AccountDO accountById = accountService.getAccountById(3);

        System.out.println(accountById);

        PageModel<AccountDO> accountWithPage = accountService.getAccountWithPage(1, 3);

        System.out.println(accountWithPage);
        System.out.println(accountWithPage.getList());

        accountWithPage = accountService.getAccountWithPage(3, 3);

        System.out.println(accountWithPage);
        System.out.println(accountWithPage.getList());

        accountWithPage = accountService.getAccountWithPage(4, 3);

        System.out.println(accountWithPage);
        System.out.println(accountWithPage.getList());

    }
}