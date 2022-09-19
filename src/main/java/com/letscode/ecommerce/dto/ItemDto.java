package com.letscode.ecommerce.dto;

import lombok.Data;

@Data
public class ItemDto {
    
    private Long id;
    private Long idProduto;
    private Integer quantidade;
    private Long idPedido;


}
