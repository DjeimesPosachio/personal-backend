package com.personal.controllers;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UserResponseDto;

import com.personal.entities.User;
import com.personal.repositories.IUserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuario", description = "Endpoints de exemplo")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<UserResponseDto> create (@RequestBody UserRequestDto dto) {
        User user = repository.save(new User(dto));
        return ResponseEntity.ok(new UserResponseDto(user));
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UserResponseDto> GetAll() {
        return repository.findAll().stream().map(UserResponseDto::new).toList();
    }

}
