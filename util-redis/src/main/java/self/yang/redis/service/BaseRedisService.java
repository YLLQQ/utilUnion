package self.yang.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * self.yang.redis.service.BaseRedisService
 *
 * @author eleven
 * @date 2019/09/25
 */
public class BaseRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * String类型，设置新值
     *
     * @param key
     * @param t
     */
    protected <T extends Serializable> void putValueToString(String key, T t) {
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);

        boundValueOperations.set(t);
    }

    /**
     * String类型，获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    protected <T extends Serializable> T getValueFromString(String key) {
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);

        return (T) boundValueOperations.get();
    }
}