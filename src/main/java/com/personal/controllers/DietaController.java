package com.personal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.repositories.DietaRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Dieta", description = "Endpoints de exemplo")
@RestController
@RequestMapping("/v1/diet")
public class DietaController {

    @Autowired
    private DietaRepository repository;

}