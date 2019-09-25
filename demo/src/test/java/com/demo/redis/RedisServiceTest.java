package com.demo.redis;

import com.demo.DemoApplicationTests;
import com.demo.demo.domain.AccountDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * com.demo.redis.RedisServiceTest
 *
 * @author eleven
 * @date 2019/09/25
 */
public class RedisServiceTest extends DemoApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSetValueToString() {
        AccountDO accountDO = new AccountDO();

        accountDO.setId(1);
        accountDO.setAccount("admin");
        accountDO.setPassword("123456");

        redisService.setValueToString(accountDO);
    }
}