package com.todoapp.todo_backend.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Items, String>, JpaRepository<Items, String>{
	
	
	
	
//CRUD Repository methods
//JPA repository allready extends CRUD Repo it provides additional features like pagination, sorting etc
//	save(S entity) → Save or update an entity.
//	findById(ID id) → Retrieve an entity by its ID.
//	findAll() → Retrieve all entities.
//	deleteById(ID id) → Delete an entity by ID.
//	existsById(ID id) → Check if an entity exists.
//	findAll(Pageable pageable) → Get paginated results.
//	findAll(Sort sort) → Get sorted results.
	
	

}
