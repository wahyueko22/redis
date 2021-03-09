package com.example.springbootrediscache.repository;

import java.util.Map;

public interface RedisRepository {

	public boolean isExistKey(String key);

	public boolean isExistHashField(String key, String field);
	
	public Object getHashFieldValue(String key, String hashKey);

	public Map<Object, Object> getAllHash(String key);

	public void saveHashes(String key, Map<String, String> map, long minutes);
	
	public long deleteKey(String[] keys);
	
	public boolean deleteHashField(String key, String hashKey);
}
