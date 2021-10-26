package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.CarrinhoPedido;
import com.elbarak.elbarakvendas.model.Pedido;
import com.elbarak.elbarakvendas.repository.CarrinhoPedidoRepository;
import com.elbarak.elbarakvendas.repository.PedidoRepository;
import com.elbarak.elbarakvendas.service.PedidoService;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(value = "/todos/carrinho")
    public List<Pedido> obterTodosComCarrinho() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidoRepository.findAll().forEach(pedido -> {
            List<CarrinhoPedido> produtosDoPedido = carrinhoPedidoRepository.findByPedidoId(pedido.getId());
            pedido.setCarrinhoPedidos(new HashSet<>(produtosDoPedido));
            pedidos.add(pedido);
        });
        return pedidos;
    }

    @GetMapping(value = "/nao-status/carrinho/{id}")
    public List<Pedido> obterStatusNaoComCarrinho(@PathVariable final Long id) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidoRepository.findByStatusIdNot(id).forEach(pedido -> {
            pedido.setCarrinhoPedidos(new HashSet<>(carrinhoPedidoRepository.findByPedidoId(pedido.getId())));
            pedidos.add(pedido);
        });
        return pedidos;
    }

    @GetMapping(value = "/status/carrinho/{id}")
    public List<Pedido> obterStatusComCarrinho(@PathVariable final Long id) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidoRepository.findByStatusId(id).forEach(pedido -> {
            pedido.setCarrinhoPedidos(new HashSet<>(carrinhoPedidoRepository.findByPedidoId(pedido.getId())));
            pedidos.add(pedido);
        });
        return pedidos;
    }

    @GetMapping(value = "/carrinho/{id}")
    public Pedido obterPorIdComCarrinho(@PathVariable final Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        List<CarrinhoPedido> produtosDoPedido = carrinhoPedidoRepository.findByPedidoId(id);
        pedido.setCarrinhoPedidos(new HashSet<>(produtosDoPedido));
        return pedido;
    }

    @PostMapping(value = "/carrinho")
    public Pedido criarComCarrinho(@RequestBody final Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        pedido.getCarrinhoPedidos().forEach(carrinhoPedido -> {
            carrinhoPedido.setPedido(pedidoSalvo);
            carrinhoPedidoRepository.save(carrinhoPedido);
        });
        return pedido;
    }

    @PutMapping(value = "/carrinho")
    public Pedido atualizarComCarrinho(@RequestBody final Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        pedido.getCarrinhoPedidos().forEach(carrinhoPedido -> {
            carrinhoPedido.setPedido(pedidoSalvo);
            carrinhoPedidoRepository.save(carrinhoPedido);
        });
        return pedido;
    }
}
