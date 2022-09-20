package com.letscode.ecommerce.services;

import java.util.List;

import com.letscode.ecommerce.models.Produto;


public interface ProdutoService {

    List<Produto> listarTodosProdutos();

    Produto novoProduto(Produto produto);

    boolean atualizaProduto(Produto produto);

    boolean deletaProduto(Long id);
}
