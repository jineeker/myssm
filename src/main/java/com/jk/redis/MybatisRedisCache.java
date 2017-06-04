package com.jk.redis;

import com.jk.util.SerializeUtil;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 实现MyBatis的默认Cache，用redis的缓存功能
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2016/10/18 16:52
 */
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

    protected Jedis createReids() {
        String host = "localhost";
        String port = "6379";
        String timeout = "3000";
        Properties prop =  new  Properties();
        InputStream in = MybatisRedisCache.class.getClassLoader().getResourceAsStream( "redis.properties" );
        try  {
            prop.load(in);
            host = prop.getProperty( "redis.ip" ).trim();
            port = prop.getProperty( "redis.port" ).trim();
            timeout = prop.getProperty( "redis.pool.maxWait" ).trim();
        }  catch  (IOException e) {
            e.printStackTrace();
        }

        JedisPool pool = new JedisPool(new JedisPoolConfig(),host ,Integer.parseInt(port),Integer.parseInt(timeout));
        return pool.getResource();
    }
}