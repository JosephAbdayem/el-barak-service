package com.elbarak.elbarakvendas.service;

import com.elbarak.elbarakvendas.model.Pedido;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;
import com.elbarak.elbarakvendas.predicate.impl.PedidoPredicate;
import com.elbarak.elbarakvendas.repository.PedidoRepository;
import com.elbarak.elbarakvendas.repository.RepositoryGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends ServiceGenerico<Pedido> {

    PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    protected RepositoryGenerico<Pedido> getRepositoryGenerico() {
        return pedidoRepository;
    }

    @Override
    protected SearchPredicate<Pedido> getSearchPredicate(String search) {
        return new PedidoPredicate(search);
    }
}
