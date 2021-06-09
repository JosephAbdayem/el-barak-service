package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.CarrinhoPedido;
import com.elbarak.elbarakvendas.model.Pedido;
import com.elbarak.elbarakvendas.repository.CarrinhoPedidoRepository;
import com.elbarak.elbarakvendas.repository.PedidoRepository;
import com.elbarak.elbarakvendas.service.PedidoService;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController extends ControllerGenerico<Pedido> {

    PedidoService pedidoService;

    @Autowired
    private CarrinhoPedidoRepository carrinhoPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    protected ServiceGenerico<Pedido> getServiceGenerico() {
        return pedidoService;
    }

    @GetMapping(value = "/carrinho/{id}")
    public Pedido obterPorId(@PathVariable final Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        List<CarrinhoPedido> produtosDoPedido = carrinhoPedidoRepository.findByPedidoId(id);
        pedido.setCarrinhoPedidos(new HashSet<>(produtosDoPedido));
        return pedido;
    }
}
