package com.todoapp.todo_backend.items;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {
	
	private static final String ADD_TOPIC = "todo-added";
	private static final String MODIFY_TOPIC = "todo-modified";

	@Autowired
	private ItemsRepository itemsrepository;
	
	private final JdbcTemplate jdbcTemplate;   // Spring Boot automatically configures and provides a JdbcTemplate bean
	private final KafkaTemplate<String,String> kafkaTemplate;

	@Autowired   // Constructor-based dependency injection (preferred method)
	public ItemsService(JdbcTemplate jdbcTemplate, KafkaTemplate<String,String> kafkaTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	    this.kafkaTemplate = kafkaTemplate;
	}

	
	public String getItems() {
		
		return "";
	}
	
	public List<Items> getAllItems(){
		
		//using JPA
//		List<Items> todoitems = new ArrayList<>();
//		itemsrepository.findAll().forEach(todoitems::add);
//		return todoitems;
//		return new ArrayList<>(itemsrepository.findAll());
		
		
		//using jdbc
		String sql = "Select * from items";
		  return jdbcTemplate.query(sql, new ResultSetExtractor<List<Items>>() {
	            @Override
	            public List<Items> extractData(ResultSet rs) throws SQLException {
	                List<Items> items = new ArrayList<>();
	                while (rs.next()) {
	                    // Create a new Item object for each row in the result set
	                    Items item = new Items();
	                    item.setId(rs.getString("id"));
	                    item.setName(rs.getString("name"));
	                    item.setDescription(rs.getString("description"));
	                    item.setStatus(rs.getString("status"));
	                 // Convert java.sql.Date to LocalDate
	                    Date dueDate = rs.getDate("due_date");
	                    item.setDueDate(dueDate != null ? dueDate.toLocalDate() : null);   
	                    System.out.println(item.toString());
	                    items.add(item);  // Add the item to the list
	                }
	                return items;  // Return the list of items
	            }
	            
	        });
		
	}
	
	
	public void addItem(Items item) {
		//jpa
		Items responseItem = itemsrepository.save(item);
		//jdbc
//		jdbcTemplate.execute(getItems());
		
		
		//publishing message to kafka
		String message = String.format("To-Do Item Added: Name=%s, Description=%s", responseItem.getName(), responseItem.getDescription());
		kafkaTemplate.send(ADD_TOPIC , message);
	
	}
	
	public void deleteItem(String id) {
		itemsrepository.deleteById(id);;
		
	}
	
	public void modifyItem(Items item) {
		Items responseItem = itemsrepository.save(item);
		
		//publishing message to kafka
		String message = String.format("To-Do Item Modified: Name=%s, Description=%s", responseItem.getName(), responseItem.getDescription());
		kafkaTemplate.send(MODIFY_TOPIC , message);
			
	}
	
	
	

}
