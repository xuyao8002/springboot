package com.xuyao.springboot.service.impl;

import com.xuyao.springboot.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements ILikeService {

    private String keySeparator = "::";

    private String mapKeyLike = "MAP_KEY_LIKE";

    private String mapKeyLikeCount = "MAP_KEY_LIKE_COUNT";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void like(Long from, Long to) {
        redisTemplate.opsForHash().put(mapKeyLike, getKey(from, to), 1);
        redisTemplate.opsForHash().increment(mapKeyLikeCount, to.toString(), 1);
    }

    @Override
    public void unlike(Long from, Long to) {
        redisTemplate.opsForHash().put(mapKeyLike, getKey(from, to), -1);
        redisTemplate.opsForHash().increment(mapKeyLikeCount, to.toString(), -1);
    }

    private String getKey(Long from, Long to) {
        return from + keySeparator + to;
    }

}
