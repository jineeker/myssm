package com.jk.redis;

import com.jk.util.SerializeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 手动操作redis缓存（与mybatis缓存属于不同客户端连接，不冲突）
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2016/10/18 15:38
 */
@Component
public class RedisCache {

	private static Logger logger = Logger.getLogger(RedisCache.class);
	public final static String CAHCENAME="cache";//缓存名
	public final static int CAHCETIME=120;//默认缓存时间,单位：s/秒

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public <T> boolean putCache(String key, T obj) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = SerializeUtil.serialize(obj);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}

	public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = SerializeUtil.serialize(obj);
			redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

//	public <T> boolean putListCache(String key, List<T> objList) {
//		final byte[] bkey = key.getBytes();
//		final byte[] bvalue = SerializeUtil.serializeList(objList);
//		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//			@Override
//			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//				return connection.setNX(bkey, bvalue);
//			}
//		});
//		return result;
//	}

//	public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
//		final byte[] bkey = key.getBytes();
//		final byte[] bvalue = SerializeUtil.serializeList(objList);
//		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//			@Override
//			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//				connection.setEx(bkey, expireTime, bvalue);
//				return true;
//			}
//		});
//		return result;
//	}

	public Object getCache(final String key) {
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return SerializeUtil.unserialize(result);
	}

//	public <T> List<T> getListCache(final String key, Class<T> targetClass) {
//		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
//			@Override
//			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
//				return connection.get(key.getBytes());
//			}
//		});
//		if (result == null) {
//			return null;
//		}
//		return SerializeUtil.deserializeList(result, targetClass);
//	}

	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}

	/**
	 * 清空所有缓存
	 */
	public void clearCache() {
		deleteCacheWithPattern("*");
	}
}
