package com.letscode.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Produto;

public interface ProdutoDao extends JpaRepository<Produto, Long> {
    
}
