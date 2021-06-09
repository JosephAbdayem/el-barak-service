package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.EntidadeGenerica;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class ControllerGenerico<T extends EntidadeGenerica> {

    @Autowired
    protected abstract ServiceGenerico<T> getServiceGenerico();

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> salvar(@RequestBody T objeto) {
        return new ResponseEntity<>(getServiceGenerico().salvar(objeto), HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping(value = "{id}")
    public ResponseEntity<T> buscarEntidadePorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(getServiceGenerico().buscarEntidadePorId(id));
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity<T> atualizarPorId(@PathVariable Long id, @RequestBody final T objeto) {
        return new ResponseEntity<>(getServiceGenerico().atualizarPorId(id, objeto), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/nao-logico/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFisicamente(@PathVariable Long id) {
        getServiceGenerico().deleteFisicamente(id);
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<T> deletar(@PathVariable Long id) {
        Optional<T> obj = Optional.ofNullable(getServiceGenerico().deletar(id));
        return obj.map(result -> new ResponseEntity<>(getServiceGenerico().salvar(result), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @ResponseBody
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Page<T>> pesquisar(final Pageable pageable,
                                             final @RequestParam(required = false, value = "search") String search,
                                             final @RequestParam(value = "isPageable", defaultValue = "false")
                                                     Boolean isPageable) {
        return new ResponseEntity<>(getServiceGenerico().pesquisar(pageable, search, isPageable), HttpStatus.OK);
    }
}
