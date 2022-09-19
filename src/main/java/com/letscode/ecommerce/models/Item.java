package com.letscode.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private final Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private final Pedido pedido;

    
    public Item(Produto produto, Integer quantidade, Pedido pedido) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade; 
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

}
