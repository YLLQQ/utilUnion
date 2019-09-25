package com.demo.redis;

import com.demo.DemoApplicationTests;
import com.demo.demo.domain.AccountDO;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String hashKey = "hash1234563";

        AccountDO accountDO = new AccountDO();

        accountDO.setId(1);
        accountDO.setAccount("admin");

        redisService.putValueToHash(hashKey, accountDO.getId() + "", accountDO);

        HashMap<String, AccountDO> objectObjectHashMap = Maps.newHashMap();

        objectObjectHashMap.put(11 + "", accountDO);
        objectObjectHashMap.put(12 + "", accountDO);
        objectObjectHashMap.put(13 + "", accountDO);

        redisService.putValueToHash(hashKey, objectObjectHashMap);

        AccountDO valueFromHash = redisService.getValueFromHash(hashKey, "11");

        System.out.println(valueFromHash);

        Map<String, AccountDO> valueFromHash1 = redisService.getValueFromHash(hashKey);

        System.out.println(valueFromHash1);

        Boolean aBoolean = redisService.hashHasKey(hashKey, "11");

        System.out.println(aBoolean);

        aBoolean = redisService.hashHasKey(hashKey, "115");

        System.out.println(aBoolean);

        redisService.deleteHashKeyFromHash(hashKey, "11", "13");

        List<AccountDO> valueFromHash2 = redisService.getValueFromHash(hashKey, "1", "2");

        System.out.println(valueFromHash2);
    }
}