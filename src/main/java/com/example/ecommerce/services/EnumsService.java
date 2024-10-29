package com.example.ecommerce.services;

import com.example.ecommerce.enums.Color;
import com.example.ecommerce.enums.Gender;
import com.example.ecommerce.enums.Size;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EnumsService {
    public List<String> getAllColors() {
        return Stream.of(Color.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    public List<String> getAllGenders() {
        return Stream.of(Gender.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    public List<String> getAllSizes() {
        return Stream.of(Size.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
