package com.example.ecommerce.enums.controllers;

import com.example.ecommerce.enums.sevices.EnumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enums")
public class EnumsController {

    private final EnumsService enumsService;

    @Autowired
    public EnumsController(EnumsService enumsService) {
        this.enumsService = enumsService;
    }

    @GetMapping("/colors")
    public List<String> getAllColors(){
        return enumsService.getAllColors();
    }
    @GetMapping("/genders")
    public List<String> getAllGenders(){
        return enumsService.getAllGenders();
    }
    @GetMapping("/sizes")
    public List<String> getAllSizes(){
        return enumsService.getAllSizes();
    }
}
