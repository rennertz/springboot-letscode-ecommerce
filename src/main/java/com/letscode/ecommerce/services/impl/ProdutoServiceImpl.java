package com.letscode.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ProdutoDao;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoDao produtoDao;

    @Override
    public List<Produto> listarTodosProdutos() {
        return produtoDao.findAll();
    }

    @Override
    public boolean novoProduto(Produto produto) {
        try {
            Produto novoProduto = new Produto(produto.getNome(), produto.getPreco(), produto.getDescricao());
            produtoDao.save(novoProduto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean atualizaProduto(Produto produto) {
        if (produto.getId() == null) {
            return false;
        }

        try {
            produtoDao.save(produto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletaProduto(Long id) {
        try {
            produtoDao.deleteById(id);
            return true;            
        } catch (Exception e) {
            return false;
        }
    }
}
