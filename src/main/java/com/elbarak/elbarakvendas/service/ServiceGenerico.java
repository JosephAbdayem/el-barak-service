package com.elbarak.elbarakvendas.service;

import com.elbarak.elbarakvendas.model.EntidadeGenerica;
import com.elbarak.elbarakvendas.predicate.criteria.SearchPredicate;
import com.elbarak.elbarakvendas.repository.RepositoryGenerico;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class ServiceGenerico<T extends EntidadeGenerica> {

    @Autowired
    protected abstract RepositoryGenerico<T> getRepositoryGenerico();

    @Autowired
    protected abstract SearchPredicate<T> getSearchPredicate(String search);

    /**
     * Método genérico que salva um objeto do tipo T,
     * objeto que será especificado durante o runtime.
     *
     * @param objeto Objeto a ser salvo.
     * @return {@link T}. Objeto salvo.
     */
    public T salvar(T objeto) {
        return getRepositoryGenerico().save(objeto);
    }

    /**
     * Método genérico que atualiza um objeto do tipo T,
     * objeto que será especificado durante o runtime.
     *
     * @param objeto Objeto a ser atualizado.
     * @return {@link T}. Objeto atualizado.
     */
    public T atualizarPorId(Long id, final T objeto) {
        if (getRepositoryGenerico().existsById(id)) {
            return getRepositoryGenerico().save(objeto);
        } else {
//            throw new NotFoundException("Objeto não encontrado no banco.");
        }
        return null;
    }

    /**
     * Método genérico que deleta fisicamente um objeto do
     * tipo T, objeto que será especificado durante o runtime.
     *
     * @param id Id do objeto que será deletado.
     */
    public void deleteFisicamente(Long id) {
        getRepositoryGenerico().deleteById(id);
    }

    /**
     * Método genérico que deleta lógicamente um objeto do
     * tipo T, objeto que será especificado durante o runtime.
     *
     * @param id Id do objeto que será deletado.
     * @return {@link T}. Objeto deletado.
     */
    public T deletar(Long id) {
        Optional<T> obj = getRepositoryGenerico().findById(id);
        if (obj.isPresent()) {
            obj.get().setAtivo(false);
            return getRepositoryGenerico().save(obj.get());
        } else {
            return null;
        }
    }

    /**
     * Método genérico que efetua um Search a partir de uma lógica
     * fornecida pelo front-end usando search predicate para objetos
     * do tipo T, objetos que serão especificados durante o runtime.
     *
     * @param pageable   Objeto Pageable para a pesquisa.
     * @param search     String contendo o predicate a ser procurado.
     * @param isPageable Se a pesquisa é pageada ou não. O default é não.
     * @return {@link Page <T>}. Paginas com o resultado da pesquisa,
     * com limite de entidades por página.
     */
    public Page<T> pesquisar(Pageable pageable, String search, Boolean isPageable) {
        if (isPageable) {
            if (search != null && !search.isEmpty()) {
                BooleanExpression expression = getSearchPredicate(search).getExpression();
                return new PageImpl<>((List<T>) getRepositoryGenerico().findAll(expression));
            }
            return getRepositoryGenerico().findAll(pageable);
        }
        return pesquisaSemPagina(pageable, search);
    }

    /**
     * Método que de fato efetua a pesquisa do Search Predicate.
     *
     * @param pageable Se é para ser paginado ou não.
     * @param search   A String da pesquisa que será efetuada.
     * @return {@link Page<T>}. Páginas com o resultado da pesquisa,
     * sem limite de entidades por página.
     */
    private Page<T> pesquisaSemPagina(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            try {
                BooleanExpression expression = getSearchPredicate(search).getExpression();
                return getRepositoryGenerico().findAll(expression, pageable);
            } catch (NullPointerException e) {
//                throw new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, 4022,
//                        "Foi enviado um valor inválido para a pesquisa avançada!");
            }
        }
        return new PageImpl<>(getRepositoryGenerico().findAll());
    }

    /**
     * Método para buscar entidade por id.
     *
     * @param id Identificador da entidade.
     * @return {@link T} Retorna um objeto de uma entidade, caso exista.
     */
    public T buscarEntidadePorId(Long id) {
        return getRepositoryGenerico().findById(id).get();
//                .orElseThrow(() -> new ObjectNotFoundException("Nao Encontrado id " + id + ", do tipo "));
    }

    /**
     * Método para buscar entidade por id.
     * Não dispara erro, caso não encontre - apenas retorna nulo.
     *
     * @param id Identificador da entidade.
     * @return {@link T} Retorna um objeto de uma entidade, caso exista.
     */
    public T buscarEntidadePorIdSemErro(Long id) {
        Optional<T> objetoEncontrado = getRepositoryGenerico().findById(id);
        return objetoEncontrado.orElse(null);
    }
}
