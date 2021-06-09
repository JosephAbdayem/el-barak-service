package com.elbarak.elbarakvendas.service;

import com.elbarak.elbarakvendas.model.Status;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;
import com.elbarak.elbarakvendas.predicate.impl.StatusPredicate;
import com.elbarak.elbarakvendas.repository.RepositoryGenerico;
import com.elbarak.elbarakvendas.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends ServiceGenerico<Status> {

    StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    protected RepositoryGenerico<Status> getRepositoryGenerico() {
        return statusRepository;
    }

    @Override
    protected SearchPredicate<Status> getSearchPredicate(String search) {
        return new StatusPredicate(search);
    }
}
