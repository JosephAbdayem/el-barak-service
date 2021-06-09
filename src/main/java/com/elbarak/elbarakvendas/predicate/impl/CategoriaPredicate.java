package com.elbarak.elbarakvendas.predicate.impl;

import com.elbarak.elbarakvendas.model.Categoria;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;

public class CategoriaPredicate extends SearchPredicate<Categoria> {

    public CategoriaPredicate(String search) {
        super(Categoria.class, search);
    }
}
