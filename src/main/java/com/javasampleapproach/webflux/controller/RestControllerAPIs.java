package com.javasampleapproach.webflux.controller;

import com.javasampleapproach.webflux.Repository.MongoQuery;
import com.javasampleapproach.webflux.Repository.ReactiveCustomerRepository;
import com.javasampleapproach.webflux.Repository.RepositoryCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.javasampleapproach.webflux.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/api/customer")
public class RestControllerAPIs {
	// это коллекшия которая хранит данные в формате ключ-значение
//	Map<Long, Customer> custStores = new HashMap<Long, Customer>();

	@Autowired
	RepositoryCustomer repositoryCustomer;

	@Autowired
	ReactiveCustomerRepository reactiveCustomerRepository;

	@Autowired
	MongoQuery mongoQuery;

//    @PostConstruct
//    public void initIt() throws Exception {
//        custStores.put(Long.valueOf(1), new Customer(1, "Jack", "Smith", 20));
//        custStores.put(Long.valueOf(2), new Customer(2, "Peter", "Johnson", 25));
//    }
	
    @GetMapping("")
    public Flux<Customer> getAll() {
    	return
//				Flux.fromIterable(
    			// список - лист попадает в функцию fromIterable и превращается в поток Customer, то есть во Flux
//    			custStores // взять коллекцию hashmap
//				.entrySet() // полный набор ключ-значение
				reactiveCustomerRepository
				.findAll(sortByIdAsc());
//				.stream()//создаем поток из коллекции
//				.map(entry -> entry.getValue()) // берем каждый элемент потока и достаем оттуда значение, то есть объект кастомер
//				.collect(Collectors.toList()); // записуем результат в лист
    }
    
	@GetMapping("/{id}")
    public Mono<Customer> getCustomer(@PathVariable String id) {
		return reactiveCustomerRepository.findById(id);
    }
	
	
	@PostMapping("/post")
    public Mono<Customer> postCustomer(@RequestBody Customer customer){
		// do post
	return reactiveCustomerRepository.save(customer);
		
	}
	
	@PutMapping("/put/{id}")
	public Mono<Customer> putCustomer(@PathVariable String id, @RequestBody Customer customer){
		// reset customer.Id
		return reactiveCustomerRepository.findById(id)
				.flatMap(oldcustomer -> {
					oldcustomer.setFirstname(customer.getFirstname());
					oldcustomer.setLastname(customer.getLastname());
					oldcustomer.setAge(customer.getAge());
					return reactiveCustomerRepository.save(oldcustomer);
		});
    }
	
	@DeleteMapping("/delete/{id}")
    public Mono<Boolean> deleteMethod(@PathVariable String id) {
		// delete processing
    	Mono<Boolean> mono = reactiveCustomerRepository.deleteCustomerByCustId(id);
    	return mono;
    }


    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "custId");
    }

    @GetMapping("/bw/{letter}")
	public List<Customer> getNameBeginwith (@PathVariable String letter) {
    	List<Customer> listOfCustomers = mongoQuery.queryBeginsWith(letter);
    	return listOfCustomers;
	}

@GetMapping ("/fbn/{name}")
	public List<Customer> findByName (@PathVariable String name) {
    	return repositoryCustomer.findByFirstname(name);
}

}

//найти по фамилии, найти по возрасту, найти по возрасту более чем.
