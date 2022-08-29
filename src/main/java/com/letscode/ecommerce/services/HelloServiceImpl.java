package com.letscode.ecommerce.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloServiceImpl implements HelloService {
    public String helloWithNameAndTime(String name) {
        String localTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        return "Hello " + StringUtils.capitalize(name) + "! It's " + localTime + " now, don't forget it!";
    }
}