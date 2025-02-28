package com.todoapp.todo_backend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        userRepository.save(user);

    }
}
