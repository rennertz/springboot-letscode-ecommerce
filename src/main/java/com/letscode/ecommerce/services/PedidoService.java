package com.letscode.ecommerce.services;

import java.util.List;

import com.letscode.ecommerce.dto.ItemDto;
import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.models.Pedido;

public interface PedidoService {
    
    List<Pedido> listarTodosPedidos();

    List<Pedido> listarpedidos(Long idCliente);

    Pedido novoPedido(PedidoDto pedidoDto);

    Pedido adicionaItem(ItemDto itemdDto);

    Pedido alteraItem(ItemDto itemDto);

    Pedido removeItem(Long idItem);

    boolean efetuaPedido(Long idPedido);

    boolean removePedido(Long idPedido);

}
