package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.Usuario;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import com.elbarak.elbarakvendas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends ControllerGenerico<Usuario> {

    UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    protected ServiceGenerico<Usuario> getServiceGenerico() {
        return usuarioService;
    }
}
