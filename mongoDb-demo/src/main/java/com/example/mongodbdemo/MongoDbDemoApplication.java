package com.example.mongodbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class MongoDbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbDemoApplication.class, args);
    }

}

/*
@SpringBootApplication 는 아래의 내용을 포함함.
1. @Configuration: Tags the class as a source of bean definitions for the application context.

2. @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

3. @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers


@EnableMongoRepositories
Spring Data’s repository interfaces 를 스캔한다.

MongoTemplate을 사용해서 쿼리 날릴 수 있어용.


@RepositoryRestResource
 */

// TODO 1. 함수 그런거.. 명명 다시
// TODO 2. GridFS 사용해보기.
// TODO 3. 홈페이지로 ㄱㄱ