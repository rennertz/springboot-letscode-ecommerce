package com.letscode.ecommerce.dao;

import com.letscode.ecommerce.models.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long>{

    List<Cliente> findAllByIdOrEmail(long id, String email);
    Cliente findByEmail(String email);

}
