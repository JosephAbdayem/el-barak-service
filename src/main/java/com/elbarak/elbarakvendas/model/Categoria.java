package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Categoria extends EntidadeGenerica {

    @Column(length = 100)
    private String nome;

    /* Construtores */

    public Categoria(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo, String nome) {
        super(id, dataCadastro, dataAtualizacao, ativo);
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {
        super.setAtivo(true);
    }

    /* Getters e Setters */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /* Equals e Hash */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Categoria categoria = (Categoria) o;
        return nome.equals(categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome);
    }

    /* To String */

    @Override
    public String toString() {
        return "Status{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
