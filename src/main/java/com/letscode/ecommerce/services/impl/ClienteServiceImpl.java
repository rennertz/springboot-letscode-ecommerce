package com.letscode.ecommerce.services.impl;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.dto.ClienteDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.PerfilEnum;
import com.letscode.ecommerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Cliente> listarTodosClientes(){
        List<Cliente> lista = clienteDao.findAll();

        // exibe apenas clientes, ocultando admins
        lista = lista.stream()
            .filter(cliente -> cliente.getPerfil().equals(PerfilEnum.CLIENTE))
            .collect(Collectors.toList());

        // apaga senhas da exibição
        lista.forEach(cliente -> cliente.setSenha(""));

        return lista;
    }

    //Usando o DTO pq nosso caso (de mentirinha), precisamos de algum trabalho nele antes de chegar a camada de persistencia
    public Cliente novoCliente(ClienteDto clienteDto) {
        try {
            if (clienteDto.getId() == 0) {
                clienteDto.setSenha(passwordEncoder.encode(clienteDto.getSenha()));
            }
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getSobrenome(), clienteDto.getEmail(), clienteDto.getSexo(), clienteDto.getCpf(), clienteDto.getSenha(), PerfilEnum.CLIENTE);
            clienteDao.save(cliente);
            cliente.setSenha("");
            return cliente;
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean atualizarCliente(Cliente cliente) {
        try {
            clienteDao.save(cliente);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean removerCliente(long id) {

        // impede deleção de um admin
        Cliente cliente = clienteDao.getById(id);
        if (cliente.getPerfil().equals(PerfilEnum.ADMIN)) {
            return false;
        }

        try {
            clienteDao.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}