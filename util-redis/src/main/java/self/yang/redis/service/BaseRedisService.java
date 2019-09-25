package self.yang.redis.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * self.yang.redis.service.BaseRedisService
 *
 * @author eleven
 * @date 2019/09/25
 */
public class BaseRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 是否存在key值
     *
     * @param key
     * @return
     */
    protected boolean hasKey(String key) {
        return getRedisTemplate().hasKey(key);
    }

    /**
     * String类型，设置新值
     *
     * @param key
     * @param t
     * @param expireTime
     * @param <T>
     */
    protected <T extends Serializable> void putValueToString(String key, T t, ExpireTime expireTime) {
        BoundValueOperations boundValueOperations = getRedisTemplate().boundValueOps(key);

        if (null != expireTime) {
            boundValueOperations.set(t, expireTime.getTime(), expireTime.getTimeUnit());
        }

        boundValueOperations.set(t);
    }

    /**
     * String类型，设置新值
     *
     * @param key
     * @param t
     */
    protected <T extends Serializable> void putValueToString(String key, T t) {
        this.putValueToString(key, t, null);
    }

    /**
     * String类型，获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    protected <T extends Serializable> T getValueFromString(String key) {
        BoundValueOperations boundValueOperations = getRedisTemplate().boundValueOps(key);

        return (T) boundValueOperations.get();
    }

    @Getter
    @AllArgsConstructor
    protected enum ExpireTime {
        ONE_MINUTE(1, TimeUnit.MINUTES),
        TEN_MINUTES(10, TimeUnit.MINUTES),
        HALF_AN_HOUR(30, TimeUnit.MINUTES),
        ONE_HOUR(1, TimeUnit.HOURS),
        ONE_DAY(1, TimeUnit.DAYS);

        private long time;

        private TimeUnit timeUnit;

    }
}
