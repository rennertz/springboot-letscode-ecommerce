package com.letscode.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(optional = false)
    private Cliente cliente;
    
    @OneToMany(
        mappedBy = "pedido",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    // TODO: mudar para Set
    private List<Item> itens = new ArrayList<>();

    @Column(name = "efetuado")
    private boolean efetuado;


    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.efetuado = false;
    }
    
    public Pedido() {
    }

    public long getId() {
        return Id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public boolean isEfetuado() {
        return efetuado;
    }


    public void addItem(Item item) {
        this.itens.add(item);
    }

    public void removeItem(Item item) {
        this.itens.remove(item);
    }

    public void efetuaPedido() {
        this.efetuado = true;
    }
    
}
