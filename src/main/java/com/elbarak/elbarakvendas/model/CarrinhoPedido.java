package com.elbarak.elbarakvendas.model;

import com.elbarak.elbarakvendas.compositekey.CarrinhoPedidoId;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "carrinho_pedido")
public class CarrinhoPedido implements Serializable {

    @EmbeddedId
    private CarrinhoPedidoId id = new CarrinhoPedidoId();

    @ManyToOne
    @MapsId("pedidoId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    public CarrinhoPedido(CarrinhoPedidoId id, Pedido pedido, Produto produto, int quantidade) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoPedido that = (CarrinhoPedido) o;
        return quantidade == that.quantidade &&
                id.equals(that.id) &&
                pedido.equals(that.pedido) &&
                produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedido, produto, quantidade);
    }
}