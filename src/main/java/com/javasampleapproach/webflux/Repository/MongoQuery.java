package com.javasampleapproach.webflux.Repository;

import com.javasampleapproach.webflux.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class MongoQuery {
// это аннотация которая предоставит ссылку на созданный объект
@Autowired
ReactiveMongoTemplate reactiveMongoTemplate;

@Autowired
RepositoryCustomer repositoryCustomer;

public List<Customer> queryBeginsWith (String s) {
    System.out.println("------------- repository customer  "+repositoryCustomer);
    Flux<Customer> customerFlux = reactiveMongoTemplate.findAll(Customer.class);
    System.out.println("!!!!!!!!!!!!! this is reactive mongo template   "+reactiveMongoTemplate);
    System.out.println("!!!!!!!!!!!!! this is customer selected flux   "+customerFlux);
    return repositoryCustomer.findAll();

}
}
