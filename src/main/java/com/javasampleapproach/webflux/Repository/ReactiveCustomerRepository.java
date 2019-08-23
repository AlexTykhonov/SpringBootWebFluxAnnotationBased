package com.javasampleapproach.webflux.Repository;

import com.javasampleapproach.webflux.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// репозиторий будет работать с базой данніх
@Repository
public interface ReactiveCustomerRepository extends ReactiveCrudRepository<Customer, String> {

   // Flux<Customer> findByName(String name);
    Mono <Boolean> deleteCustomerByCustId (String custId);
    Flux<Customer> findByOrderByAgeAsc();
    Flux<Customer> findByOrderByFirstnameAsc();
    Flux<Customer> findAll(Sort sortByIdAsc);
}


//написать 5 методов репозитория используя ключевые слова и методы контроллера которые будут использовать методы репозитория.