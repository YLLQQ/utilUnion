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

        String zSetKey = "zSet123456";

        AccountDO accountDO = new AccountDO();

        accountDO.setId(1);
        accountDO.setAccount("admin");

        AccountDO accountDO1 = new AccountDO();

        accountDO1.setId(2);
        accountDO1.setAccount("test");

        AccountDO accountDO2 = new AccountDO();

        accountDO2.setId(3);
        accountDO2.setAccount("test");

        redisService.putValueToZSet(zSetKey, accountDO, 3D);
        redisService.putValueToZSet(zSetKey, accountDO1, 2D);
        redisService.putValueToZSet(zSetKey, accountDO2, 1D);
    }
}