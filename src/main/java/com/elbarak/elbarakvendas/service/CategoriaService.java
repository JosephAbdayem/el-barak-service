package com.elbarak.elbarakvendas.service;

import com.elbarak.elbarakvendas.model.Categoria;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;
import com.elbarak.elbarakvendas.predicate.impl.CategoriaPredicate;
import com.elbarak.elbarakvendas.repository.CategoriaRepository;
import com.elbarak.elbarakvendas.repository.RepositoryGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends ServiceGenerico<Categoria> {

    CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    protected RepositoryGenerico<Categoria> getRepositoryGenerico() {
        return categoriaRepository;
    }

    @Override
    protected SearchPredicate<Categoria> getSearchPredicate(String search) {
        return new CategoriaPredicate(search);
    }
}
