package test.provider.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/07/02 16:56
 * @Description 类描述:
 */

@Configuration
public class RedissonConfig {

    @Bean
    public Config config(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.0.188:6379")
                .setDatabase(6)
                .setConnectionMinimumIdleSize(10)
                .setConnectionPoolSize(60)
                .setSubscriptionConnectionPoolSize(60)
                .setIdleConnectionTimeout(5000)
                .setTimeout(5000)
                .setConnectTimeout(5000)
                .setSslEnableEndpointIdentification(false);

        return config;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(){
        RedissonClient redissonClient = Redisson.create(config());
        return redissonClient;
    }
}
