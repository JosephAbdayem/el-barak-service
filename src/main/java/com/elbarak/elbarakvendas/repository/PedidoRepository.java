package com.elbarak.elbarakvendas.repository;

import com.elbarak.elbarakvendas.model.Pedido;

import java.util.List;

public interface PedidoRepository extends RepositoryGenerico<Pedido> {

    List<Pedido> findByAtivo(boolean ativo);

    List<Pedido> findByStatusIdNot(Long id);

    List<Pedido> findByStatusId(Long id);
}
