package com.letscode.ecommerce.controllers;

import com.letscode.ecommerce.dto.DummyProductDto;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.restclient.FinanceiroRestClient;
import com.letscode.ecommerce.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProdutosEndpoints {

    @Autowired
    FinanceiroRestClient financeiroRestClient;

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(path = "/produtos", method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> getAllProducts() {
        return new ResponseEntity<List<Produto>>(produtoService.listarTodosProdutos(), HttpStatus.OK);
    }

    @RequestMapping(path = "/produtos", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(@RequestBody Produto produto) {
        boolean sucesso = produtoService.novoProduto(produto);

        if (sucesso) {
            return new ResponseEntity<String>("Produto criado com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Falha ao criar produto!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/produtos", method = RequestMethod.PUT)
    public ResponseEntity<String> changeProduct(@RequestBody Produto produto) {
        boolean sucesso = produtoService.atualizaProduto(produto);

        if (sucesso) {
            return new ResponseEntity<String>("Produto alterado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Falha ao alterar produto!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean sucesso = produtoService.deletaProduto(id);

        if (sucesso) {
            return new ResponseEntity<String>("Produto deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Falha ao deletar produto", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/produtosDummy/categorias", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCategories() {

        return ResponseEntity.ok(financeiroRestClient.findAllCategories());
    }

    @RequestMapping(path="/produtosDummy", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DummyProductDto>> getAllProductsDummy() {

        return ResponseEntity.ok(financeiroRestClient.findAllProducts());
    }
}
