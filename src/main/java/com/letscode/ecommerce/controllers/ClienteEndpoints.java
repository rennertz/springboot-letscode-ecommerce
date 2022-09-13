package com.letscode.ecommerce.controllers;

import com.letscode.ecommerce.dto.ClienteDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClienteEndpoints {
    @Autowired
    ClienteService clienteService;


    @RequestMapping(path="/cliente", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllCients() {
        List<Cliente> clienteList = clienteService.listarTodosClientes();

        return ResponseEntity.ok(clienteList);
    }

    @RequestMapping(path="/cliente", method = RequestMethod.POST)
    public ResponseEntity<Cliente> novoCliente(@RequestBody ClienteDto cliente) {
        Cliente clienteSalvo = clienteService.novoCliente(cliente);

        if(clienteSalvo != null) {
            return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("Criacao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/cliente", method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarCliente(@RequestBody Cliente cliente) {
        boolean sucesso = clienteService.atualizarCliente(cliente);

        if(sucesso) {
            return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Atualizacao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path="/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removerCliente(@PathVariable long id) {
        boolean sucesso = clienteService.removerCliente(id);

        if(sucesso) {
            return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Remocao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }
}
