package com.letscode.ecommerce.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.dto.ClienteDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.ClienteService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ClienteEndpoints {
    @Autowired
    ClienteService clienteService;

    @RequestMapping(path="/cliente", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ApiResponse(
        responseCode = "200",
        description = "Lista de contas",
        content = { @Content(mediaType = "application/json", array = 
            @ArraySchema(schema = @Schema(implementation = Cliente.class)))}
    )
    public ResponseEntity<List<Cliente>> getAllCients() {
        List<Cliente> clienteList = clienteService.listarTodosClientes();

        return ResponseEntity.ok(clienteList);
    }

    @RequestMapping(path="/cliente", method = RequestMethod.POST)
    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso!", content = 
                @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(hidden = true)))
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
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!")
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
    @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso!")
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
