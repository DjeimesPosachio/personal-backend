package com.personal.controllers;

import com.personal.entities.User;
import com.personal.repositories.IUserRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "user")

public class UserController {
    @Autowired
    IUserRepository repository;

    @GetMapping
    public List <User> getAll() {
        return repository.findAll();

    }

    @PostMapping
    public void  create(@RequestBody User user) {
        repository.save(user);
    }

}

