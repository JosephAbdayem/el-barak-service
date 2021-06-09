package com.elbarak.elbarakvendas.predicate.impl;

import com.elbarak.elbarakvendas.model.Pedido;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;

public class PedidoPredicate extends SearchPredicate<Pedido> {

    public PedidoPredicate(String search) {
        super(Pedido.class, search);
    }
}
