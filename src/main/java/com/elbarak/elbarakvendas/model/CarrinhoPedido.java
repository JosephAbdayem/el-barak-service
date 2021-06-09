package com.elbarak.elbarakvendas.model;

import com.elbarak.elbarakvendas.compositekey.CarrinhoPedidoId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
class CarrinhoPedido implements Serializable {

    @EmbeddedId
    private CarrinhoPedidoId id = new CarrinhoPedidoId();

    @ManyToOne
    @MapsId("pedidoId")
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    private Produto produto;

    private int quantidade;

    public CarrinhoPedido(
            CarrinhoPedidoId id,
            Pedido pedido,
            Produto produto,
            int quantidade
    ) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public CarrinhoPedido() {
    }

    public CarrinhoPedidoId getId() {
        return id;
    }

    public void setId(CarrinhoPedidoId id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}