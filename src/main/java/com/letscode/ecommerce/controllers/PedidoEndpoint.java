package com.letscode.ecommerce.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.dto.ItemDto;
import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.services.PedidoService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class PedidoEndpoint {

    @Autowired
    PedidoService pedidoService;

    @RequestMapping(path = "/pedido", method =  RequestMethod.GET)
    @ApiResponse(
        responseCode = "200",
        description = "Lista de pedidos",
        content = { @Content(mediaType = "application/json", array = 
            @ArraySchema(schema = @Schema(implementation = Pedido.class)))}
    )
    public ResponseEntity<List<Pedido>> getAllOrders() {
        return new ResponseEntity<>(pedidoService.listarTodosPedidos(), HttpStatus.OK);
    }

    @RequestMapping(path = "/pedido/cliente/{idCliente}", method =  RequestMethod.GET)
    @ApiResponse(
        responseCode = "200",
        description = "Lista de pedidos",
        content = { @Content(mediaType = "application/json", array = 
            @ArraySchema(schema = @Schema(implementation = Pedido.class)))}
    )
    public ResponseEntity<List<Pedido>> getClientOrders(@PathVariable Long idCliente) {
        return new ResponseEntity<>(pedidoService.listarpedidos(idCliente), HttpStatus.OK);
    }

    @RequestMapping(path = "/pedido", method = RequestMethod.POST)
    @ApiResponse(responseCode = "200", description = "Pedido criado",
        content = @Content(schema = @Schema(implementation = Pedido.class)))
    public ResponseEntity<Pedido> createOrder(@RequestBody PedidoDto pedidoDto) {
        Pedido novoPedido = pedidoService.novoPedido(pedidoDto);

        if (Objects.isNull(novoPedido)) {
            return new ResponseEntity("Falha ao criar pedido!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        }
    }

    @RequestMapping(path = "/pedido/item", method = RequestMethod.POST)
    @ApiResponse(responseCode = "200", description = "Item adicionado com sucesso!")
    public ResponseEntity<Pedido> addItem(@RequestBody ItemDto itemDto) {
        Pedido novoPedido = pedidoService.adicionaItem(itemDto);

        if (Objects.isNull(novoPedido)) {
            return new ResponseEntity("Falha ao adicionar item ao pedido!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        }
    }

    @RequestMapping(path = "/pedido/item", method = RequestMethod.PUT)
    @ApiResponse(responseCode = "200", description = "Item alterado com sucesso!")
    public ResponseEntity<Pedido> changeItem(@RequestBody ItemDto itemDto) {
        Pedido pedidoAlterado = pedidoService.alteraItem(itemDto);

        if (Objects.isNull(pedidoAlterado)) {
            return new ResponseEntity("Falha ao alterar item do pedido!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(pedidoAlterado, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/pedido/item/{idItem}", method = RequestMethod.DELETE)
    @ApiResponse(responseCode = "200", description = "Item deletado com sucesso!")
    public ResponseEntity<Pedido> removeItem(@PathVariable Long idItem) {
        Pedido pedidoAlterado = pedidoService.removeItem(idItem);

        if (Objects.isNull(pedidoAlterado)) {
            return new ResponseEntity("Falha ao remover item do pedido!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(pedidoAlterado, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/pedido/efetivar/{idPedido}", method = RequestMethod.PUT)
    @ApiResponse(responseCode = "200", description = "Pedido efetivado com sucesso!")
    public ResponseEntity<String> fulfillOrder(@PathVariable Long idPedido) {
        boolean pedidoEfetuado = pedidoService.efetuaPedido(idPedido);

        if (pedidoEfetuado) {
            return new ResponseEntity<>("Pedido efetuado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao efetivar pedido!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/pedido/{idPedido}", method = RequestMethod.DELETE)
    @ApiResponse(responseCode = "200", description = "Pedido removido com sucesso!")
    public ResponseEntity<String> deleteOrder(@PathVariable Long idPedido) {
        boolean pedidoEfetuado = pedidoService.efetuaPedido(idPedido);

        if (pedidoEfetuado) {
            return new ResponseEntity<>("Pedido removido com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao remover pedido!", HttpStatus.BAD_REQUEST);
        }
    }


}
