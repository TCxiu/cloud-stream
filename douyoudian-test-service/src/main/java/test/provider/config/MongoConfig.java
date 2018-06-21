package test.provider.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import static freemarker.template.utility.Collections12.singletonList;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/16 18:40
 * @Description 类描述:
 */

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(){

        ServerAddress serverAddress = new ServerAddress("192.168.0.80",27017);
        MongoCredential credential = MongoCredential.createCredential("root", "test", "203204".toCharArray());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
                .sslEnabled(false)
                .maxWaitTime(1000)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .heartbeatSocketTimeout(1000)
                .alwaysUseMBeans(true)
                .build();


        return new MongoClient(singletonList(serverAddress),
                                singletonList(credential),
                                 mongoClientOptions);
    }


    @Bean
    public SimpleMongoDbFactory simpleMongoDbFactory(){
        SimpleMongoDbFactory mongoFactory = new SimpleMongoDbFactory(mongoClient(),"test");
        return mongoFactory;
    }

    @Bean
    MongoTemplate mongoTemplate(){
        MongoTemplate mongoTemplate = new MongoTemplate(simpleMongoDbFactory());
        return mongoTemplate;
    }


}
