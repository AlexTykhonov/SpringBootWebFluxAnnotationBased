package com.javasampleapproach.webflux.Config;

import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@EnableReactiveMongoRepositories
    public class Config extends AbstractReactiveMongoConfiguration {

        @Override
        public MongoClient reactiveMongoClient() {
            return MongoClients.create();
        }

        @Override
        protected String getDatabaseName() {
            return "jsa_mongodb";
        }

    }

