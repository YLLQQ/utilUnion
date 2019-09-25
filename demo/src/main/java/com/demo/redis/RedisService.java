package com.demo.redis;

import com.demo.demo.domain.AccountDO;
import org.springframework.stereotype.Service;
import self.yang.redis.service.BaseRedisService;

/**
 * com.demo.demo.RedisService
 *
 * @author eleven
 * @date 2019/09/25
 */
@Service
public class RedisService extends BaseRedisService {

    public void setAccountToRedis(String key, AccountDO accountDO) {
        super.putValueToString(key, accountDO);
    }

    public AccountDO getAccountFromRedis(String key) {
        return super.getValueFromString(key);
    }

}
