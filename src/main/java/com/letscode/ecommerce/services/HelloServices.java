package com.letscode.ecommerce.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloServices {
    public String helloWithNameAndTime(String name) {
        return "Hello " + StringUtils.capitalize(name) + ", it's " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "!";
    }
}