package com.example.demo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
record Customer(Integer id, Date enrollment, String name){}
record Order(Integer id, Integer customerId){}
record SystemEvent(String message, Date date){}
@Service
class CrmService{
	private final Map<Integer, Customer> db = new ConcurrentHashMap<>();

	CrmService(){
		List.of("Asad", "Hameed", "Afridi", "Khan").forEach(name -> addCustomer(name));
	}
	Flux<Customer> getCustomer(){
	  return Flux.fromIterable(this.db.values());

	}
	List<Order> getOrdersfor(Integer customerId) throws InterruptedException {
		//Thread.sleep(1000);
		var max =  (int) (Math.random() * 10000);
		var result = new ArrayList<Order>();
		for(var id =1 ; id < max ; id++){
			result.add(new Order(id, customerId));

		}

		return  result;
	}
	private final AtomicInteger id = new AtomicInteger();
	public   Mono<Customer> addCustomer(String name){

			int i = id.getAndIncrement();

		this.db.put(i , new Customer(i, new Date(), name));
		return  Mono.just(this.db.get(i)) ;
	}

	public void deleteCustomer(Integer id) {
		this.db.remove(id);
	}
}

@Controller
class EdgGraphQl{
	//@SchemaMapping(typeName = "Query" , field = "hello")
	private  CrmService crmService;
	EdgGraphQl(CrmService crmService){
		this.crmService = crmService;
	}
	@QueryMapping
	String hello(){
		return "hello world" ;
	}

	@QueryMapping
	Flux<Customer> customers(){
		return  this.crmService.getCustomer();
	}

	@MutationMapping
	Mono<Customer> addCustomer(@Argument String name){
		System.out.println(name);
		return this.crmService.addCustomer(name);
	}
	@MutationMapping
	String deleteCustomer(@Argument Integer id){
		this.crmService.deleteCustomer(id);
		return "The customer of " + id + " is delete from system";
	}



	@SchemaMapping(typeName = "Customer", field = "enrollment")
	public  String getenrollment(Customer customer){
		var id = customer.id();
		System.out.println(" Customer id " + id);
		var date = customer.enrollment();
		return this.getDateFormat().format(date);
	}
	private ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<>();
	private SimpleDateFormat getDateFormat(){
		if(sdf.get() == null){
			this.sdf.set(new SimpleDateFormat("yyyy-MM-dd"));
		}

		return  this.sdf.get();
	}

	@SchemaMapping(typeName = "Customer")
	Mono<List<Order>>  orders(Customer customer) throws InterruptedException {
		System.out.println("---Customer id " + customer.id());

		return Mono.just(this.crmService.getOrdersfor(customer.id())).delayElement(Duration.ofSeconds(1));
	}

	@SubscriptionMapping
	Flux<SystemEvent> systemEvents(){
		var events = Stream.generate(() -> new SystemEvent("error" , new Date()));
		return  Flux.fromStream(events).take(100).delayElements(Duration.ofSeconds(1));
	}

	@SchemaMapping(typeName = "SystemEvent" , value = "date")
	String date(SystemEvent systemEvent){
		return  this.getDateFormat().format(systemEvent.date());
	}
}


//@Component
//class GreetingRunTimeConfig implements RuntimeWiringConfigurer{
//
//	@Override
//	public void configure(RuntimeWiring.Builder builder) {
//			builder.type("Query", b -> b.dataFetcher("hello", env -> "hello " + env.getArgument("name"))).build();
//	}
//}
