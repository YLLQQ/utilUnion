package com.demo.redis;

import com.demo.DemoApplicationTests;
import com.demo.demo.domain.AccountDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

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

        String zSetKey = "zSet1234563";

        Set<AccountDO> valueFromZSet = redisService.getValueFromZSet(zSetKey);

        for (AccountDO accountDO : valueFromZSet) {
            System.out.println(accountDO);
        }

        valueFromZSet = redisService.getValueFromZSet(zSetKey, true);

        for (AccountDO accountDO : valueFromZSet) {
            System.out.println(accountDO);
        }

        valueFromZSet = redisService.getValueFromZSet(zSetKey, 2D);

        for (AccountDO accountDO : valueFromZSet) {
            System.out.println(accountDO);
        }

        valueFromZSet = redisService.getValueFromZSet(zSetKey, 2D, 3D);

        for (AccountDO accountDO : valueFromZSet) {
            System.out.println(accountDO);
        }

        valueFromZSet = redisService.getValueFromZSet(zSetKey, 2D, 3D, true);

        for (AccountDO accountDO : valueFromZSet) {
            System.out.println(accountDO);
        }

        AccountDO accountDO = new AccountDO();

        accountDO.setId(1);
        accountDO.setAccount("admin");

        Double scoreInZSet = redisService.getScoreInZSet(zSetKey, accountDO);

        System.out.println(scoreInZSet);

        boolean zSetMember = redisService.isZSetMember(zSetKey, accountDO);

        System.out.println(zSetMember);

        accountDO.setAccount("admin1");

        scoreInZSet = redisService.getScoreInZSet(zSetKey, accountDO);

        System.out.println(scoreInZSet);

        zSetMember = redisService.isZSetMember(zSetKey, accountDO);

        System.out.println(zSetMember);
    }
}