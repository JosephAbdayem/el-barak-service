package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido extends EntidadeGenerica {

    @Column(nullable = false)
    private Integer mesa;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST })
    private Usuario usuario;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST })
    private Status status;

    @ManyToMany
    @JoinTable(name = "carrinho_pedido",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    /* Construtores */

    public Pedido(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo, Integer mesa, Usuario usuario,
                  Status status, List<Produto> produtos) {
        super(id, dataCadastro, dataAtualizacao, ativo);
        this.mesa = mesa;
        this.usuario = usuario;
        this.status = status;
        this.produtos = produtos;
    }

    public Pedido(Integer mesa, Usuario usuario, Status status, List<Produto> produtos) {
        this.mesa = mesa;
        this.usuario = usuario;
        this.status = status;
        this.produtos = produtos;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /* Equals e Hash */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pedido pedido = (Pedido) o;
        return mesa.equals(pedido.mesa) && usuario.equals(pedido.usuario)
                && status.equals(pedido.status) && produtos.equals(pedido.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mesa, usuario, status, produtos);
    }

    /* To String */

    @Override
    public String toString() {
        return "Pedido{" +
                "mesa=" + mesa +
                ", usuario=" + usuario +
                ", status=" + status +
                ", produtos=" + produtos +
                '}';
    }
}
