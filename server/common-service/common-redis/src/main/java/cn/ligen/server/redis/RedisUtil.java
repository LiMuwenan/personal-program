package cn.ligen.server.redis;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ligen
 * @date 2023/9/22 23:06
 * @description redis 封装
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /*  =============================================过期时间=============================================  */
    /**
     * 指定key过期时间
     * @param key key
     * @param time 过期时间
     * @param unit 时间单位
     */
    public void expire(String key, Long time, TimeUnit unit) {
        redisTemplate.expire(key, time, unit);
    }

    /**
     * 指定key过期时间，默认单位：秒
     * @param key key
     * @param time 过期时间
     */
    public void expire(String key, Long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取指定key过期时间
     * @param key key
     * @return 返回过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取指定key过期时间
     * @param key key
     * @return 返回过期时间
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /*  =============================================字符串操作=============================================  */
    /**
     * 设置字符串类型
     * @param key key
     * @param value value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置字符串类型，过期时间单位：秒
     * @param key key
     * @param value value
     * @param time 过期时间
     */
    public void set(String key, Object value, Long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 设置字符串类型，过期时间单位：秒
     * @param key key
     * @param value value
     * @param time 过期时间
     * @param unit 过期时间单位
     */
    public void set(String key, Object value, Long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 获取字符串key对象
     * @param key key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /*  =============================================列表=============================================  */

    /**
     * 列表右侧插入，一次性插入多个值
     * @param key key
     * @param values 多个value值
     */
    public void lrPush(String key, Object... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 列表左侧插入，一次性插入多个值
     * @param key key
     * @param values 多个value值
     */
    public void llPush(String key, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 列表指定位置设置值
     * @param key key
     * @param index 指定下标
     * @param value value
     */
    public void lSet(String key, Long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 列表左侧弹出值
     * @param key key
     * @return
     */
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 列表右侧弹出值
     * @param key key
     * @return
     */
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
    /*  =============================================哈希表=============================================  */

    /**
     * 设置哈希表值
     * @param key redis key
     * @param valueKey 值的key
     * @param value redis值的值
     */
    public void hSet(String key, Object valueKey, Object value) {
        redisTemplate.opsForHash().put(key, valueKey, value);
    }

    /**
     * 设置多个哈希表值
     * @param key key
     * @param value map对象
     */
    public void hmSet(String key, Map<?, ?> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    /**
     * 从哈希表中获取一个值
     * @param key key
     * @param valueKey valueKey
     */
    public Object hGet(String key, Object valueKey) {
        return redisTemplate.opsForHash().get(key, valueKey);
    }

    /**
     * 获取指定key的所有值
     * @param key key
     * @return
     */
    public Map<Object, Object> hGetKey(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除指定key中哈希表的key
     * @param key redis key
     * @param valuesKeys 哈希表key
     */
    public void hDelete(String key, Object... valuesKeys) {
        redisTemplate.opsForHash().delete(key, valuesKeys);
    }

    /**
     * 指定redis key中是否存在某个key
     * @param key redis key
     * @param valueKey 是否存在的value key
     * @return
     */
    public Boolean hHasKey(String key, Object valueKey) {
        return redisTemplate.opsForHash().hasKey(key, valueKey);
    }

    /*  =============================================集合=============================================  */

    /**
     * 向集合中设置多个值
     * @param key key
     * @param values 多个value
     */
    public void sAdd(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取指定redis key的集合
     * @param key key
     * @return
     */
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 检查指定redis key的集合中是否存在某个值
     * @param key key
     * @param value value
     * @return
     */
    public Boolean sHasValue(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 删除指定redis key中的指定元素
     * @param key key
     * @param values 指定的多个元素
     */
    public void sRemove(String key, Object... values) {
        redisTemplate.opsForSet().remove(key ,values);
    }

}
