package com.elbarak.elbarakvendas.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends EntidadeGenerica {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String login;

    @Column(length = 50, nullable = false)
    private String senha;

    /* Construtores */

    public Usuario(Long id, Date dataCadastro, Date dataAtualizacao, boolean ativo, String nome, String login,
                   String senha) {
        super(id, dataCadastro, dataAtualizacao, ativo);
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {
        super.setAtivo(true);
    }

    /* Getters e Setters */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /* Equals e Hash */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return nome.equals(usuario.nome) && login.equals(usuario.login) && senha.equals(usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome, login, senha);
    }

    /* To String */

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
