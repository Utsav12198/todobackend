package com.todoapp.todo_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.todoapp.todo_backend.items.Introduction;

@Configuration
public class AppConfig {
	
	
	@Bean
	public Introduction introduction() {   //method name can be anything based on choice or naming convention.
		return new Introduction();
	}
	

}


