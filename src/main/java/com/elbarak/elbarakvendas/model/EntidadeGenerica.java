package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class EntidadeGenerica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Column(nullable = false)
    private boolean ativo;

    /* Cosntrutores */

    public EntidadeGenerica(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = ativo;
    }

    public EntidadeGenerica() {
        this.ativo = true;
    }

    /* Getters e Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /* Equals e Hash */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadeGenerica that = (EntidadeGenerica) o;
        return ativo == that.ativo && id.equals(that.id) && dataCadastro.equals(that.dataCadastro)
                && Objects.equals(dataAtualizacao, that.dataAtualizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCadastro, dataAtualizacao, ativo);
    }

    /* Método de auto preenchimento */

    @PrePersist
    public void autoDataCadastro() {
        this.dataCadastro = new Date();
    }

    @PreUpdate
    public void autoDataAtualizacao() {
        this.dataAtualizacao = new Date();
    }

    /* To String */

    @Override
    public String toString() {
        return "EntidadeGenerica{" +
                "id=" + id +
                ", dataCadastro=" + dataCadastro +
                ", dataAtualizacao=" + dataAtualizacao +
                ", ativo=" + ativo +
                '}';
    }
}
