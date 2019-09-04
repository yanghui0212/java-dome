package com.yangqh.common.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.yangqh.model.UserModel;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * @author yangq
 * Create time in 2018-07-18 14:16
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName());
                sb.append('.').append(method.getName());
                // FIXME 参数太长的时候请指定key属性，否则key太长
                for (Object obj : params) {
                    if (obj != null) {
                        sb.append(obj.toString());
                    }
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //设置CacheManager的值序列化方式为 GenericJackson2JsonRedisSerializer,但其实RedisCacheConfiguration默认使用StringRedisSerializer序列化key，
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
