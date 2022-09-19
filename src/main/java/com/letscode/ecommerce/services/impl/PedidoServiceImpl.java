package com.letscode.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.dao.PedidoDao;
import com.letscode.ecommerce.dto.ItemDto;
import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoDao pedidoDao;

    @Autowired
    ClienteDao clienteDao;

    @Override
    public List<Pedido> listarTodosPedidos() {
        return pedidoDao.findAll();
    }

    @Override
    public List<Pedido> listarpedidos(Long idCliente) {
        return pedidoDao.findAllByClienteId(idCliente);
    }

    @Override
    public Pedido novoPedido(PedidoDto pedidoDto) {
        Optional<Cliente> clienteOpt = clienteDao.findById(pedidoDto.getIdCliente());
        
        if (clienteOpt.isPresent()) {
            Pedido pedido = new Pedido(clienteOpt.get());
            pedidoDao.save(pedido);
            return pedido;
        } else {
            return null;
        }
    }

    @Override
    public Pedido adicionaItem(Long idPedido, ItemDto itemDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido alteraItem(Long idPedido,  ItemDto itemDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido removeItem(long idPedido, long idItem) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean efetuaPedido(Long idPedido) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removePedido(Long idPedido) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
