package com.todoapp.todo_backend.items;

import org.springframework.beans.factory.annotation.Value;

public class Introduction {
	
	
	@Value("${website.name}")
	private String myWebsiteName;
	
	
	public String welcomeMessage() {
		return myWebsiteName;
	}

}
