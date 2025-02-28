package com.todoapp.todo_backend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {
	@Bean
	public NewTopic todoEventsTopic() {
		return new NewTopic("todo-events", 3, (short) 1); // Create a topic with 3 partitions and replication factor 1
	}
	
	@Bean
	public NewTopic todoAddedTopic() {
	    return new NewTopic("todo-added", 1, (short) 1);  // Topic for "added" events
	}

	@Bean
	public NewTopic todoModifiedTopic() {
	    return new NewTopic("todo-modified", 1, (short) 1);  // Topic for "modified" events
	}

}
