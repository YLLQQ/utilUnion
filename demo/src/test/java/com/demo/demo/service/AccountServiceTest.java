package com.demo.demo.service;

import com.demo.demo.DemoApplicationTests;
import com.demo.demo.domain.AccountDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * com.demo.demo.service.AccountServiceTest
 *
 * @author eleven
 * @date 2019/09/21
 */
public class AccountServiceTest extends DemoApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    public void test() {
        AccountDO accountById = accountService.getAccountById(3);

        System.out.println(accountById);

    }
}