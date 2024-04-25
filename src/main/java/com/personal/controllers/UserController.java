package com.personal.controllers;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UserResponseDto;
import com.personal.entities.User;
import com.personal.repositories.IUserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuario", description = "Endpoints de exemplo")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserRepository repository;

    // @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Async
    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody UserRequestDto data) {
        return ResponseEntity.ok(repository.save(new User(data)));
    }

    // @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UserResponseDto> GetAll() {
        return repository.findAll().stream().map(UserResponseDto::new).toList();
    }

    @DeleteMapping
    public String delete() {
        return "alguma coisa";
    }

    @PutMapping
    public String update() {
        return "update";
    }

}
