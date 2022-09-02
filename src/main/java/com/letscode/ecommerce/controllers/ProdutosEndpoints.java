package com.letscode.ecommerce.controllers;

import com.letscode.ecommerce.restclient.FinanceiroRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProdutosEndpoints {

    @Autowired
    FinanceiroRestClient financeiroRestClient;

    @RequestMapping(path="/produtos/categorias", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCategories() {

        //TODO outras acoes da API

        return ResponseEntity.ok(financeiroRestClient.findAll());
    }
}
