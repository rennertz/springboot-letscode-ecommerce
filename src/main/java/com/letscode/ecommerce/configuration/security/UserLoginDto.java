package com.letscode.ecommerce.configuration.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {

    private String email;
    private String senha;

}
