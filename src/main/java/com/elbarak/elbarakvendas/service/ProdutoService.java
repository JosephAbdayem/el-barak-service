package com.elbarak.elbarakvendas.service;

import com.elbarak.elbarakvendas.model.Produto;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;
import com.elbarak.elbarakvendas.predicate.impl.ProdutoPredicate;
import com.elbarak.elbarakvendas.repository.ProdutoRepository;
import com.elbarak.elbarakvendas.repository.RepositoryGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends ServiceGenerico<Produto> {

    ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    protected RepositoryGenerico<Produto> getRepositoryGenerico() {
        return produtoRepository;
    }

    @Override
    protected SearchPredicate<Produto> getSearchPredicate(String search) {
        return new ProdutoPredicate(search);
    }
}
