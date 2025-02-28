

package com.todoapp.todo_backend.items;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController   //RestController (Controller + ResponseBody)  //@controller only return html and view not a string so in this case we need to explicitly mention @ResponseBody to return anything other than HTLM JSP pages from the method
public class ItemsController {
	

	
	private Introduction intro;
	
	//@Autowired is optional in case there is only one constructor and spring will automatically inject dependencies
	public ItemsController(Introduction intro) {
		this.intro = intro;
	}
	
	@Autowired
	private ItemsService itemsservice;
	
	@RequestMapping("/items")
	public String getItems() {
		return "Hello";
	}
	
	@GetMapping("/getallitems")
	public List<Items> getAllItems(){
		return itemsservice.getAllItems();	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/additem")
	public void addItem(@RequestBody Items item) {
		itemsservice.addItem(item);
		System.out.println(item.toString());
	
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/modifyitem")
	public void modifyItem(@RequestBody Items item) {
		itemsservice.modifyItem(item);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteitem/{id}")
	public void deleteItem( @PathVariable String id) {
		System.out.println("id "+id);
		itemsservice.deleteItem(id);
	}

	@GetMapping("/welcome")
	public String welcomeMessage() {
		return intro.welcomeMessage();
	}

	@PreAuthorize("hasRole('ADMIN')")    //@PreAuthorize runs before method execution
	@GetMapping("/admin")
	public String welcomeMessageForAdmin() {
//		return intro.welcomeMessage();
		return "Welcome to the page admin";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String welcomeMessageForUser() {
		return "Welcome to the page user";
	}

	
	

}
