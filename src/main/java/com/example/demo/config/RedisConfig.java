package com.example.demo.config;

import com.example.demo.props.CusRedisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Spring boot会根据application.properties配置文件自动装载RedisConnectionFactory,RedisTemplate以及RedisSerializer等组件。
 * 故此处不需要手动配置以上对象，否则会报错。
 */
@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private final CusRedisProperties redisProperties = new CusRedisProperties();

    private RedisConnectionFactory redisConnectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    @ConditionalOnMissingBean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.redisConnectionFactory != null) {
            return this.redisConnectionFactory;
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setBlockWhenExhausted(false);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        // 获取单机配置Redis
        RedisStandaloneConfiguration redisStandaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        jedisConnectionFactory.setHostName("192.168.1.51");
        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setPassword(this.redisProperties.getPassword());
        this.redisConnectionFactory = jedisConnectionFactory;
        return this.redisConnectionFactory;
    }

    @Bean(name = "RedisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // RedisTemplate会自动初始化StringRedisSerializer，所以不需要设置
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 设置字符串序列化器以处理Key
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setConnectionFactory(this.initRedisConnectionFactory());
        return redisTemplate;
    }
}
