package com.rest.api.jdbcjpa.jpa.dbconfig;


import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Named("baseDao")
@SuppressWarnings("unchecked")
public class BaseDaoImpl<C, P> implements IDAO<C, P> {

    private static final long serialVersionUID = -3849916011537202461L;

    @Getter
    @Setter
    @Inject
    protected EntityManager entityManager;

    @Override
    public C find(P id, Class<C> entityClass) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public Collection<C> findAll(String instruction, Class<C> entityClass) {
        TypedQuery<C> query = this.entityManager.createQuery(instruction, entityClass);
        return query.getResultList();
    }

    @Override
    public void update(P id, Class<C> entityClass) {
        this.entityManager.merge(find(id, entityClass));
    }

    @Override
    public void save(C entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public void remove(P id, Class<C> entityClass) {
        this.entityManager.remove(find(id, entityClass));
    }

    @Override
    public TypedQuery<C> createCustomQuery(String query, Class<C> entityClass, Object... params) {
        TypedQuery<C> sql = entityManager.createQuery(query, entityClass);

        for (int i = 1; i <= params.length; i++) {
            sql.setParameter(i, params[i]);
        }
        return sql;
    }
}
