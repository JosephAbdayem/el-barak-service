package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.Pedido;
import com.elbarak.elbarakvendas.service.PedidoService;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController extends ControllerGenerico<Pedido> {

    PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    protected ServiceGenerico<Pedido> getServiceGenerico() {
        return pedidoService;
    }
}
