package com.example.springbootrediscache.repository;

import com.example.springbootrediscache.models.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class UserRepositoryImpl implements  UserRepository {
	
    private RedisTemplate<String, User> redisTemplate;
    private HashOperations hashOperations; //to access redis cache
    private ValueOperations<String, String> ops;

    public UserRepositoryImpl(@Qualifier("userRedisTemplate")RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void save(User user) {
        hashOperations.put("USER",user.getId(),user);
        redisTemplate.expire("USER",60, TimeUnit.SECONDS);
       
    }

    @Override
    public Map<String,User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public User findById(String id) {
        return (User)hashOperations.get("USER",id);
    }

    @Override
    public void update(User user) {
      save(user);
    }

    @Override
    public void delete(String id) {
       hashOperations.delete("USER",id);
    }
}
