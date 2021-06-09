package com.elbarak.elbarakvendas.repository;

import com.elbarak.elbarakvendas.model.EntidadeGenerica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface RepositoryGenerico<T extends EntidadeGenerica>
        extends JpaRepository<T, Long>, PagingAndSortingRepository<T, Long>, QuerydslPredicateExecutor<T> {
}
