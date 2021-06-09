package com.elbarak.elbarakvendas.predicate.impl;

import com.elbarak.elbarakvendas.model.Produto;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;

public class ProdutoPredicate extends SearchPredicate<Produto> {

    public ProdutoPredicate(String search) {
        super(Produto.class, search);
    }
}
