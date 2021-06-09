package com.elbarak.elbarakvendas.repository;

import com.elbarak.elbarakvendas.compositekey.CarrinhoPedidoId;
import com.elbarak.elbarakvendas.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrinhoPedidoRepository extends JpaRepository<CarrinhoPedido, CarrinhoPedidoId> {

    List<CarrinhoPedido> findByPedidoId(Long id);

}