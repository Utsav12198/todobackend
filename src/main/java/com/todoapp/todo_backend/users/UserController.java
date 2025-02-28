package com.todoapp.todo_backend.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/signup")
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }




}
