package com.demo.redis;

import com.demo.demo.domain.AccountDO;
import org.springframework.stereotype.Service;
import self.yang.redis.service.BaseRedisService;
import self.yang.util.tool.TimeUtil;

/**
 * com.demo.demo.RedisService
 *
 * @author eleven
 * @date 2019/09/25
 */
@Service
public class RedisService extends BaseRedisService {

    public void setValueToString(AccountDO accountDO) {
        super.putValueToString(TimeUtil.getCurrentDateTime(), accountDO);
    }


}
