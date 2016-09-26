package com.jk.redis;

import com.jk.util.SerializeUtil;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class MybatisRedisCache implements Cache {

    private static Logger logger = Logger.getLogger(MybatisRedisCache.class);
    private Jedis redisClient = createReids();
    /**
     * The ReadWriteLock.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {

        return Integer.valueOf(redisClient.dbSize().toString());
    }

    @Override
    public void putObject(Object key, Object value) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value+"\n");
        redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
    } 

    @Override
    public Object getObject(Object key) {
        Object value = SerializeUtil.unserialize(redisClient.get(SerializeUtil.serialize(key.toString())));
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>getObject:" + key + "=" + value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);
    }

    @Override
    public void clear() {
        redisClient.flushDB();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    protected static Jedis createReids() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1",6379,3000);
        return pool.getResource();
    }
}