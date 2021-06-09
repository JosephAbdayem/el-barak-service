package com.elbarak.elbarakvendas.predicate.impl;

import com.elbarak.elbarakvendas.model.Usuario;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;

public class UsuarioPredicate extends SearchPredicate<Usuario> {

    public UsuarioPredicate(String search) {
        super(Usuario.class, search);
    }
}
