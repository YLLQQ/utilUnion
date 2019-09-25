package self.yang.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import self.yang.redis.common.ExpireTimeEnum;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * self.yang.redis.service.BaseRedisService
 *
 * @author eleven
 * @date 2019/09/25
 */
public class BaseRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    protected RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 删除key
     *
     * @param key
     */
    protected void deleteKey(String key) {
        getRedisTemplate().delete(key);
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
    protected <T extends Serializable> void putValueToString(String key, T t, ExpireTimeEnum expireTime) {
        BoundValueOperations boundValueOperations = getRedisTemplate().boundValueOps(key);

        if (null != expireTime) {
            boundValueOperations.set(t, expireTime.getTime(), expireTime.getTimeUnit());
        } else {
            boundValueOperations.set(t);
        }
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
     * Set类型，获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    protected <T> T getValueFromString(String key) {
        BoundValueOperations boundValueOperations = getRedisTemplate().boundValueOps(key);

        return (T) boundValueOperations.get();
    }

    /**
     * Set类型，设置新值
     *
     * @param key
     * @param ts
     */
    public <T extends Serializable> void putValueToSet(String key, ExpireTimeEnum expireTime, T... ts) {
        BoundSetOperations boundSetOperations = getRedisTemplate().boundSetOps(key);

        boundSetOperations.add(ts);

        if (null != expireTime) {
            boundSetOperations.expire(expireTime.getTime(), expireTime.getTimeUnit());
        }
    }

    /**
     * Set类型，设置新值
     *
     * @param key
     * @param ts
     */
    public <T extends Serializable> void putValueToSet(String key, T... ts) {
        this.putValueToSet(key, null, ts);
    }

    /**
     * Set类型，获取成员
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getSetMembers(String key) {
        BoundSetOperations boundSetOperations = getRedisTemplate().boundSetOps(key);

        return boundSetOperations.members();
    }

    /**
     * Set类型，是否包含成员
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean isSetMember(String key, T t) {
        BoundSetOperations boundSetOperations = getRedisTemplate().boundSetOps(key);

        return boundSetOperations.isMember(t);
    }

    /**
     * List类型，添加新值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Serializable> void putValueToList(String key, T t) {
        this.putValueToList(key, null, t);
    }

    /**
     * List类型，添加新值
     *
     * @param key
     * @param expireTimeEnum
     * @param t
     * @param <T>
     */
    public <T extends Serializable> void putValueToList(String key, ExpireTimeEnum expireTimeEnum, T t) {
        BoundListOperations boundListOperations = getRedisTemplate().boundListOps(key);

        boundListOperations.rightPush(key, t);

        if (null != expireTimeEnum) {
            boundListOperations.expire(expireTimeEnum.getTime(), expireTimeEnum.getTimeUnit());
        }
    }

    /**
     * List类型，添加新值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Serializable> void putValueToList(String key, T... ts) {
        BoundListOperations boundListOperations = getRedisTemplate().boundListOps(key);

        for (T t : ts) {
            boundListOperations.rightPush(t);
        }
    }

    /**
     * List类型，添加新值
     *
     * @param key
     * @param expireTimeEnum
     * @param ts
     * @param <T>
     */
    public <T extends Serializable> void putValueToList(String key, ExpireTimeEnum expireTimeEnum, T... ts) {
        BoundListOperations boundListOperations = getRedisTemplate().boundListOps(key);

        for (T t : ts) {
            boundListOperations.rightPush(t);
        }

        if (null != expireTimeEnum) {
            boundListOperations.expire(expireTimeEnum.getTime(), expireTimeEnum.getTimeUnit());
        }
    }

    /**
     * List类型，获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getValueFromList(String key) {
        BoundListOperations boundListOperations = getRedisTemplate().boundListOps(key);

        return boundListOperations.range(0, boundListOperations.size());
    }

    /**
     * List类型，获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getValueFromList(String key, long index) {
        BoundListOperations boundListOperations = getRedisTemplate().boundListOps(key);

        return (T) boundListOperations.index(index);
    }

    /**
     * List类型，获取值
     *
     * @param key
     * @param startIndex
     * @param endIndex
     * @param <T>
     * @return
     */
    public <T> List<T> getValueFromList(String key, long startIndex, long endIndex) {
        BoundListOperations boundListOperations = getRedisTemplate().boundListOps(key);

        return boundListOperations.range(startIndex, endIndex);
    }

    /**
     * ZSet类型，添加新值
     *
     * @param key
     * @param t
     * @param score
     * @param <T>
     */
    public <T extends Serializable> void putValueToZSet(String key, T t, Double score) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        boundZSetOperations.add(t, score);
    }

    /**
     * ZSet类型，添加新值
     *
     * @param key
     * @param typedTuples
     * @param <T>
     */
    public <T extends Serializable> void putValueToZSet(String key, Set<ZSetOperations.TypedTuple<T>> typedTuples) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        boundZSetOperations.add(typedTuples);
    }
}
