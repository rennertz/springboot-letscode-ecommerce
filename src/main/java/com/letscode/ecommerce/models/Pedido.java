package com.letscode.ecommerce.models;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    // TODO: Ocultar informações sensíveis do cliente (especialmente senha e perfil de acesso)
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    @OneToMany(
        mappedBy = "pedido",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Item> itens = new LinkedHashSet<>();

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

    public Set<Item> getItens() {
        return itens;
    }

    public boolean getEfetuado() {
        return efetuado;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(Set<Item> itens) {
        this.itens = itens;
    }

    public void setEfetuado(boolean efetuado) {
        this.efetuado = efetuado;
    }

    // public void addItem(Item item) {
    //     this.itens.add(item);
    // }

    // public void removeItem(Item item) {
    //     this.itens.remove(item);
    // }

    
}
