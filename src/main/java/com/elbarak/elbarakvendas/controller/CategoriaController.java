package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.Categoria;
import com.elbarak.elbarakvendas.service.CategoriaService;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends ControllerGenerico<Categoria> {

    CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    protected ServiceGenerico<Categoria> getServiceGenerico() {
        return categoriaService;
    }
}
