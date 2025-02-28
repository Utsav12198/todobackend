package com.todoapp.todo_backend.items;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Items {
	
	@Id
	private String id;
	private String name;
	private String description;
	private String status;
	
	@Column(name = "due_date")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String toString() {
		
		return "Items " +id + " " + name + " " + description + " " + status + " " + dueDate;
			
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		


	}

}
