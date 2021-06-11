package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Pedido extends EntidadeGenerica {

    @Column(nullable = false)
    private Integer mesa;

    @ManyToOne(cascade = { CascadeType.MERGE})
    private Usuario usuario;

    @ManyToOne(cascade = { CascadeType.MERGE})
    private Status status;

    @Transient
    private Set<CarrinhoPedido> carrinhoPedidos;

    /* Construtores */

    public Pedido(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo, Integer mesa, Usuario usuario, Status status, Set<CarrinhoPedido> carrinhoPedidos) {
        super(id, dataCadastro, dataAtualizacao, ativo);
        this.mesa = mesa;
        this.usuario = usuario;
        this.status = status;
        this.carrinhoPedidos = carrinhoPedidos;
    }

    public Pedido(Integer mesa, Usuario usuario, Status status, Set<CarrinhoPedido> carrinhoPedidos) {
        this.mesa = mesa;
        this.usuario = usuario;
        this.status = status;
        this.carrinhoPedidos = carrinhoPedidos;
    }

    public Pedido() {
        super.setAtivo(true);
    }

    /* Getters e Setters */

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<CarrinhoPedido> getCarrinhoPedidos() {
        return carrinhoPedidos;
    }

    public void setCarrinhoPedidos(Set<CarrinhoPedido> carrinhoPedidos) {
        this.carrinhoPedidos = carrinhoPedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pedido pedido = (Pedido) o;
        return mesa.equals(pedido.mesa) &&
                usuario.equals(pedido.usuario) &&
                status.equals(pedido.status) &&
                carrinhoPedidos.equals(pedido.carrinhoPedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mesa, usuario, status, carrinhoPedidos);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "mesa=" + mesa +
                ", usuario=" + usuario +
                ", status=" + status +
                '}';
    }
}
