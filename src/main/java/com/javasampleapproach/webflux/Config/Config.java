package com.javasampleapproach.webflux.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@EnableReactiveMongoRepositories
    public class Config extends AbstractReactiveMongoConfiguration {

    //монгоклиент - обтект который обеспечивает соединение с БД
    @Autowired
    MongoClient mongoClient;

        @Override
        public MongoClient reactiveMongoClient() {
            return mongoClient;
        }

        @Override
        protected String getDatabaseName() {
            return "jsa_mongodb";
        }

// компонент это объект которым управляет спринг
        @Bean
        public MongoClient mongoClient(){
            return MongoClients.create();
        }
        // ком
// это кастомный компонент которым управляет спринг (значит может создать обьект и дать на него ссылку)
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
      // реактивмонготемплецт это обьект который позволяет делать запросы к базе данных
        return new ReactiveMongoTemplate(mongoClient, getDatabaseName());
    }


    }

