package com.oktadeveloper.graphqldemo;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqldemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(FoodService foodService) {
	    return args -> {
	        Stream.of("Pizza", "Spam", "Eggs", "Avocado").forEach(name -> {
	            Food food = new Food();
	            food.setName(name);
	            foodService.saveFood(food);
	        });
	        foodService.getFoods().forEach(System.out::println);
	    };
	}
	
	
	@Bean
	public FilterRegistrationBean<TransactionFilter> loggingFilter(){
	    FilterRegistrationBean<TransactionFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new TransactionFilter());
	    registrationBean.addUrlPatterns("/graphql/*");
	        
	    return registrationBean;    
	}
}
