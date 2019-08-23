package com.javasampleapproach.webflux.Repository;

import com.javasampleapproach.webflux.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryCustomer  extends MongoRepository<Customer, String> {

     List<Customer> findByFirstname (String name);
}
