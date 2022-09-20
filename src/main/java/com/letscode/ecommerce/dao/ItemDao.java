package com.letscode.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Item;

public interface ItemDao extends JpaRepository<Item, Long>{
    
}
