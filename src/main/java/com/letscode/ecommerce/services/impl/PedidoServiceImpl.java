package com.letscode.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.dao.ItemDao;
import com.letscode.ecommerce.dao.PedidoDao;
import com.letscode.ecommerce.dao.ProdutoDao;
import com.letscode.ecommerce.dto.ItemDto;
import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.Item;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoDao pedidoDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    ProdutoDao produtoDao;

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
    public Pedido adicionaItem(ItemDto itemDto) {
        Optional<Pedido> pedidoOpt = pedidoDao.findById(itemDto.getIdPedido());
        Optional<Produto> produtoOpt = produtoDao.findById(itemDto.getIdProduto());
        Integer quantidade = itemDto.getQuantidade();

        if (pedidoOpt.isPresent() && produtoOpt.isPresent() && quantidade > 0) {
            Item item = new Item(produtoOpt.get(), quantidade, pedidoOpt.get());
            itemDao.save(item);

            Pedido pedido = pedidoDao.findById(itemDto.getIdPedido()).get();
            return pedido;
        } else {
            return null;
        }
    }

    @Override
    public Pedido alteraItem(ItemDto itemDto) {
        Optional<Pedido> pedidoOpt = pedidoDao.findById(itemDto.getIdPedido());
        Optional<Produto> produtoOpt = produtoDao.findById(itemDto.getIdProduto());
        Integer quantidade = itemDto.getQuantidade();

        if (pedidoOpt.isPresent() && produtoOpt.isPresent() && quantidade > 0) {
            Item item = new Item(produtoOpt.get(), quantidade, pedidoOpt.get());
            item.setId(itemDto.getId());
            itemDao.save(item);

            Pedido pedido = pedidoDao.findById(itemDto.getIdPedido()).get();
            return pedido;
        } else {
            return null;
        }
    }

    @Override
    public Pedido removeItem(Long idItem) {
        try {
            Long idPedido = itemDao.findById(idItem).get().getPedido().getId();
            itemDao.deleteById(idItem);
            Pedido pedido = pedidoDao.findById(idPedido).get();
            return pedido;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean efetuaPedido(Long idPedido) {
        Pedido pedido = pedidoDao.findById(idPedido).get();
        pedido.setEfetuado(true);
        pedidoDao.save(pedido);
        return true;
    }

    @Override
    public boolean removePedido(Long idPedido) {
        try {
            clienteDao.deleteById(idPedido);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
}
