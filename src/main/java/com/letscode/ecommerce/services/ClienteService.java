package com.letscode.ecommerce.services;

import com.letscode.ecommerce.dto.ClienteDto;
import com.letscode.ecommerce.models.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarTodosClientes();

    Cliente novoCliente(ClienteDto cliente);

    boolean atualizarCliente(Cliente cliente);

    boolean removerCliente(long id);

}
