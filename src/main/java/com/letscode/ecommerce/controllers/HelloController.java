package com.letscode.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.services.HelloService;

@RestController
public class HelloController {
    
    @Autowired
    HelloService helloServices;

    @RequestMapping(path = "hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }

    @RequestMapping(path = "hello/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> helloWithName(@PathVariable String name) {
        return new ResponseEntity<>("Hello " + StringUtils.capitalize(name) + "!", HttpStatus.OK);
    }
   
    @RequestMapping(path = "hello/{name}/horario", method = RequestMethod.GET)
    public ResponseEntity<String> helloWithNameAndTime(@PathVariable String name) {
        String response = helloServices.helloWithNameAndTime(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
