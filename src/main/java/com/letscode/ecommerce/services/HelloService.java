package com.letscode.ecommerce.services;

public interface HelloService {
    
    /**
     * Retorna uma saldação contendo nome e horário local do servidor
     * @param name nome fornecido no request
     * @return saudação
     */
    String helloWithNameAndTime(String name);
}
