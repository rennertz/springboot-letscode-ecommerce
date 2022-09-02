package com.letscode.ecommerce.dao;

import com.letscode.ecommerce.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long>{

}
