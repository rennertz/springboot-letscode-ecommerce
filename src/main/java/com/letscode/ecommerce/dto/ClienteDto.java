package com.letscode.ecommerce.dto;

import com.letscode.ecommerce.models.PerfilEnum;

import lombok.Data;

@Data
public class ClienteDto {

    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String sexo;
    private String cpf;
    private String senha;
    private PerfilEnum perfil;

}
