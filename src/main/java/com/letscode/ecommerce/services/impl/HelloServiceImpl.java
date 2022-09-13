package com.letscode.ecommerce.services.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.letscode.ecommerce.controllers.HelloController;
import com.letscode.ecommerce.services.HelloService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloServiceImpl implements HelloService {

    Logger log = LoggerFactory.getLogger(HelloController.class);
    public String helloWithNameAndTime(String name) {
        String localTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        log.info("Saudação personalizada com horário transmitida com sucesso para "+ name +"!");
        return "Hello " + StringUtils.capitalize(name) + "! It's " + localTime + " now, don't forget it!";
    }
}