package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Status extends EntidadeGenerica {

    @Column(length = 100)
    private String nome;

    /* Construtores */

    public Status(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo, String nome) {
        super(id, dataCadastro, dataAtualizacao, ativo);
        this.nome = nome;
    }

    public Status(String nome) {
        this.nome = nome;
    }

    public Status() {
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
        Status status = (Status) o;
        return nome.equals(status.nome);
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