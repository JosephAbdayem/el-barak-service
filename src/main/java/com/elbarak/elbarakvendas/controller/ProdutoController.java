package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.Produto;
import com.elbarak.elbarakvendas.service.ProdutoService;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController extends ControllerGenerico<Produto> {

    ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    protected ServiceGenerico<Produto> getServiceGenerico() {
        return produtoService;
    }
}
