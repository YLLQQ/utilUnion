package com.demo.demo.service;

import com.demo.demo.DemoApplicationTests;
import com.demo.demo.domain.AccountDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import self.yang.mybatis.sql.WhereUnionNextEnum;
import self.yang.mybatis.sql.WhereValueOperatorEnum;
import self.yang.mybatis.sql.WhereCondition;

import java.util.List;

/**
 * com.demo.demo.service.AccountServiceTest
 *
 * @author eleven
 * @date 2019/09/21
 */
public class AccountServiceTest extends DemoApplicationTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LogService logService;

    @Test
    public void test() {
//        long start;
//        long end;
//
//        for (int i = 0; i < 5; i++) {
//            start = TimeUtil.getCurrentMillions();
//            accountService.listAll();
//            end = TimeUtil.getCurrentMillions();
//            System.out.println(end - start);
//
//            start = TimeUtil.getCurrentMillions();
//            accountService.listAllWithColumns();
//            end = TimeUtil.getCurrentMillions();
//            System.out.println(end - start);
//
//            start = TimeUtil.getCurrentMillions();
//            logService.listAllWithColumns();
//            end = TimeUtil.getCurrentMillions();
//            System.out.println(end - start);
//
//            start = TimeUtil.getCurrentMillions();
//            logService.listAll();
//            end = TimeUtil.getCurrentMillions();
//            System.out.println(end - start);
//
//            System.out.println(">>>>>>>>>>");
//        }

//        accountService.deleteById(1);

//        accountService.listAllWithColumns();
//
//        List<AccountDO> accountDOS = accountService.listAllWithOptionalColumns("id", "account");
//
//        System.out.println(accountDOS);
//
//        AccountDO accountDO = accountService.getById(2);
//
//        System.out.println(accountDO);
//
//        AccountDO accountDO1 = new AccountDO();
//
//        accountDO1.setId(2);
//        accountDO1.setAccount("admin");
//
//        boolean b = accountService.updateById(accountDO1);
//
//        System.out.println(b);
//
//        AccountDO accountDO2 = new AccountDO();
//
//        accountDO2.setId(3);
//        accountDO2.setAccount("admin");
//        accountDO2.setPassword("admin");
//
//        b = accountService.updateById(accountDO2);
//
//        System.out.println(b);
//
//        AccountDO accountDO3 = new AccountDO();
//
//        accountDO3.setAccount(System.currentTimeMillis() + "");
//        accountDO3.setPassword("admin");
//
//        b = accountService.add(accountDO3);
//
//        System.out.println(b);
//
        List<AccountDO> accountDOS = accountService.listAllWithWhereString(
                new WhereCondition("id", 3, WhereValueOperatorEnum.equals, WhereUnionNextEnum.and));

        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("id", 3, WhereValueOperatorEnum.not_equals, null));
//
//        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("id", 3, WhereValueOperatorEnum.larger, null));
//
//        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("id", 3, WhereValueOperatorEnum.larger_equals, null));
//
//        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("id", 3, WhereValueOperatorEnum.lower, null));
//
//        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("id", 3, WhereValueOperatorEnum.lower_equals, null));
//
//        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("id", null, WhereValueOperatorEnum.is_null, null));
//
//        System.out.println(accountDOS);
//
//        accountDOS = accountService.listAllWithWhereString(
//                new WhereCondition("account", "adm", WhereValueOperatorEnum.like, null));
//
//        System.out.println(accountDOS);

        accountDOS = accountService.listAllWithColumnsAndWhereString(
                new WhereCondition("id", 3, WhereValueOperatorEnum.not_equals, WhereUnionNextEnum.and),
                new WhereCondition("account", "admin", WhereValueOperatorEnum.like, WhereUnionNextEnum.or)
        );

        System.out.println(accountDOS);

        accountDOS = accountService.listAllWithWhereString(
                new WhereCondition("id", 3, WhereValueOperatorEnum.not_equals, null),
                new WhereCondition("account", "admin", WhereValueOperatorEnum.like, null)
        );

        System.out.println(accountDOS);

        accountDOS = accountService.listAllWithWhereString(
                new WhereCondition("id", 3, WhereValueOperatorEnum.not_equals, WhereUnionNextEnum.and)
                , new WhereCondition("account", "admin", WhereValueOperatorEnum.like, WhereUnionNextEnum.or)
                , new WhereCondition("password", "123456", WhereValueOperatorEnum.equals, null)
        );

        System.out.println(accountDOS);

    }
}