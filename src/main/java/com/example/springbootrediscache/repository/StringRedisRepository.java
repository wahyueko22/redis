package com.example.springbootrediscache.repository;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.example.springbootrediscache.models.User;

@Component
public class StringRedisRepository implements RedisRepository{
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	
	@Override
	public boolean isExistKey(String key) {
		return stringRedisTemplate.hasKey(key);
	}
		
	@Override
	public boolean isExistHashField(String key, String hashKey) {
		return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
	}
	
	@Override
	public Object getHashFieldValue(String key, String hashKey) {
		return stringRedisTemplate.opsForHash().get(key, hashKey);
	}
	
	@Override
	public Map<Object, Object> getAllHash(String key) {
		return stringRedisTemplate.opsForHash().entries(key);
	}
	
	@Override
	public void saveHashes(String key, Map<String, String> map, long minutes) {
		stringRedisTemplate.opsForHash().putAll(key, map);		
		stringRedisTemplate.expire(key, minutes, TimeUnit.MINUTES);
	}
		
	@Override
	public long deleteKey(String[] keys) {
		try {
			stringRedisTemplate.delete(Arrays.asList(keys));
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public boolean deleteHashField(String key, String hashKey) {
		stringRedisTemplate.opsForHash().delete(key, hashKey);
		return true;
	}
	
	public void saveValue(String key, String value, Long expireTimeInSeconds) {
		stringRedisTemplate.opsForValue().set(key, value);
		stringRedisTemplate.expire(key, expireTimeInSeconds, TimeUnit.SECONDS);
	}
	
	public void setKeyValue(String key, String value, Long expireTimeInSeconds) {
		stringRedisTemplate.opsForValue().set(key, value);
		stringRedisTemplate.expire(key, expireTimeInSeconds, TimeUnit.SECONDS);
	}
	
	public String getValue(String key) {
		// TODO Auto-generated method stub
		return stringRedisTemplate.opsForValue().get(key);
	}

}
