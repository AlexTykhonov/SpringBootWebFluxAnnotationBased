package com.javasampleapproach.webflux.Repository;

import com.javasampleapproach.webflux.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

// репозиторий будет работать с базой данніх
@Repository
public interface ReactiveCustomerRepository extends ReactiveCrudRepository<Customer, String> {

   // Flux<Customer> findByName(String name);
}
