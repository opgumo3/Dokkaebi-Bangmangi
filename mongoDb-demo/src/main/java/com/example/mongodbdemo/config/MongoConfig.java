package com.example.mongodbdemo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    // java code based connection MongoDB
    // https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.mongo-java-config

    // MongoDB driver API 엔트리 포인트!
/*    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }*/

    // 더 자세히 설정을 하기 위해서는 factory bean 사용!

    /*
     * Factory bean that creates the com.mongodb.client.MongoClient instance
     */
    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("localhost");
        mongo.setPort(27017);
        mongo.setMongoClientSettings(clientSettings());
        return mongo;
    }

    @Bean
    public MongoClientSettings clientSettings() {
        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyToConnectionPoolSettings(builder -> ConnectionPoolSettings.builder().maxSize(1).build())
//                .credential(MongoCredential.createCredential("", "", ""))
                .build();

        return settings;
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(), "demo");
    }
}
