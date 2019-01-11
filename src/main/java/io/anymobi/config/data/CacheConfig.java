/*
package io.anymobi.config.data;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@EnableCaching
//@EnableRedisRepositories(basePackages = "io.anymobi.repositories.redis")
public class CacheConfig {

    @Autowired
    private Environment env;

    @Bean
    public RedissonClient redissonClient() {
        Config redisConfig = new Config();
        redisConfig.useSingleServer()
                .setAddress("redis://" + env.getProperty("redis.host") + ":" + env.getProperty("redis.port"))
                .setConnectionMinimumIdleSize(5)
                .setConnectionPoolSize(10);
        return Redisson.create(redisConfig);
    }
}
*/
