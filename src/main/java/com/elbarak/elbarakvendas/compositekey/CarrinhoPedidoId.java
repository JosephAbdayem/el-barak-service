package com.elbarak.elbarakvendas.compositekey;

import com.elbarak.elbarakvendas.model.EntidadeGenerica;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarrinhoPedidoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pedidoId;
    private Long produtoId;

    public CarrinhoPedidoId(Long pedidoId, Long produtoId) {
        super();
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
    }

    public CarrinhoPedidoId() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoPedidoId that = (CarrinhoPedidoId) o;
        return pedidoId.equals(that.pedidoId) && produtoId.equals(that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, produtoId);
    }
}
