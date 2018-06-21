package test.provider.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 09:59
 * @Description 类描述:
 */

@Configuration
public class RabbitMqConfig {


    @Bean
    public ConnectionFactory connectionFactory(){

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.0.80");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setConnectionTimeout(6000);
        connectionFactory.setChannelCheckoutTimeout(6000);
        return connectionFactory;
    }
}
