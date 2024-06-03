package com.personal.controllers;

import com.personal.commons.Constants;
import com.personal.services.EnunsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/enums")
@RequiredArgsConstructor
public class EnunsController {

    private final EnunsService enumService;

    @GetMapping("/all")
    public Map<String, List<Map<String, String>>> getAllEnums() {
        return enumService.getAllEnums(Constants.PATH_ENUM);
    }
}
