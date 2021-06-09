package com.elbarak.elbarakvendas.service;

import com.elbarak.elbarakvendas.model.Usuario;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;
import com.elbarak.elbarakvendas.predicate.impl.UsuarioPredicate;
import com.elbarak.elbarakvendas.repository.RepositoryGenerico;
import com.elbarak.elbarakvendas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceGenerico<Usuario> {

    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected RepositoryGenerico<Usuario> getRepositoryGenerico() {
        return usuarioRepository;
    }

    @Override
    protected SearchPredicate<Usuario> getSearchPredicate(String search) {
        return new UsuarioPredicate(search);
    }
}
