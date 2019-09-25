package self.yang.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import self.yang.redis.common.ExpireTimeEnum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
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
    public void deleteKey(String key) {
        getRedisTemplate().delete(key);
    }

    /**
     * 是否存在key值
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
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
    public <T extends Serializable> void putValueToString(String key, T t, ExpireTimeEnum expireTime) {
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
    public <T extends Serializable> void putValueToString(String key, T t) {
        this.putValueToString(key, t, null);
    }

    /**
     * Set类型，获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getValueFromString(String key) {
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

        return getValueFromList(key, 0, boundListOperations.size());
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
        this.putValueToZSet(key, null, t, score);
    }

    /**
     * ZSet类型，添加新值
     *
     * @param key
     * @param expireTimeEnum
     * @param t
     * @param score
     * @param <T>
     */
    public <T extends Serializable> void putValueToZSet(String key, ExpireTimeEnum expireTimeEnum, T t, Double score) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        boundZSetOperations.add(t, score);

        if (null != expireTimeEnum) {
            boundZSetOperations.expire(expireTimeEnum.getTime(), expireTimeEnum.getTimeUnit());
        }
    }

    /**
     * ZSet类型，添加新值
     *
     * @param key
     * @param typedTuples
     * @param <T>
     */
    public <T extends Serializable> void putValueToZSet(
            String key,
            DefaultTypedTuple<T>... typedTuples
    ) {
        this.putValueToZSet(key, null, typedTuples);
    }

    /**
     * ZSet类型，添加新值
     *
     * @param key
     * @param expireTimeEnum
     * @param typedTuples
     * @param <T>
     */
    public <T extends Serializable> void putValueToZSet(
            String key,
            ExpireTimeEnum expireTimeEnum,
            DefaultTypedTuple<T>... typedTuples
    ) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        Set<ZSetOperations.TypedTuple<T>> typedTupleSet = new HashSet<>(Arrays.asList(typedTuples));

        boundZSetOperations.add(typedTupleSet);

        if (null != expireTimeEnum) {
            boundZSetOperations.expire(expireTimeEnum.getTime(), expireTimeEnum.getTimeUnit());
        }
    }

    /**
     * ZSet类型，获取值。默认根据score升序返回
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getValueFromZSet(String key) {
        return getValueFromZSet(key, false);
    }

    /**
     * ZSet类型，获取值
     *
     * @param key
     * @param reverse true，score升序返回；false，score降序返回
     * @param <T>
     * @return
     */
    public <T> Set<T> getValueFromZSet(String key, boolean reverse) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        if (reverse) {
            return boundZSetOperations.reverseRange(0, boundZSetOperations.size());
        }

        return boundZSetOperations.range(0, boundZSetOperations.size());
    }

    /**
     * ZSet类型，获取值
     *
     * @param key
     * @param score
     * @param <T>
     * @return
     */
    public <T> Set<T> getValueFromZSet(String key, double score) {
        return getValueFromZSet(key, score, score);
    }

    /**
     * ZSet类型，获取值。默认根据score升序返回
     *
     * @param key
     * @param minScore
     * @param maxScore
     * @param <T>
     * @return
     */
    public <T> Set<T> getValueFromZSet(String key, double minScore, double maxScore) {
        return this.getValueFromZSet(key, minScore, maxScore, false);
    }

    /**
     * ZSet类型，获取值。默认根据score升序返回
     *
     * @param key
     * @param minScore
     * @param maxScore
     * @param reverse
     * @param <T>
     * @return
     */
    public <T> Set<T> getValueFromZSet(String key, double minScore, double maxScore, boolean reverse) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        if (reverse) {
            return boundZSetOperations.reverseRangeByScore(minScore, maxScore);
        }

        return boundZSetOperations.rangeByScore(minScore, maxScore);
    }

    /**
     * 获取对象在ZSet中Score
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public <T> Double getScoreInZSet(String key, T t) {
        BoundZSetOperations boundZSetOperations = getRedisTemplate().boundZSetOps(key);

        return boundZSetOperations.score(t);
    }

    /**
     * ZSet类型，是否是成员
     *
     * @param key
     * @param t
     * @return
     */
    public <T> boolean isZSetMember(String key, T t) {
        return null != getScoreInZSet(key, t);
    }
}
