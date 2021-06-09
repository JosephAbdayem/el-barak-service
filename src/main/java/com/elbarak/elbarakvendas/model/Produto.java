package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produto extends EntidadeGenerica {

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private Float preco;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST })
    private Categoria categoria;

    /* Construtores */

    public Produto(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo, String nome, String descricao,
                   Float preco, Categoria categoria) {
        super(id, dataCadastro, dataAtualizacao, ativo);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto(String nome, String descricao, Float preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto() {
        super.setAtivo(true);
    }

    /* Getters e Setters */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /* Equals e Hash */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome) && Objects.equals(descricao, produto.descricao)
                && preco.equals(produto.preco) && categoria.equals(produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome, descricao, preco, categoria);
    }

    /* To String */

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                '}';
    }
}
