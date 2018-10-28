package com.rest.api.jdbcjpa.jpa.dbconfig;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collection;

public interface IDAO<T, P> extends Serializable {

    T find(P id, Class<T> entityClass);

    Collection<T> findAll(String instruction, Class<T> entityClass);

    void update(P id, Class<T> entityClass);

    void save(T entity);

    void remove(P id, Class<T> entityClass);

    TypedQuery<T> createCustomQuery(String query, Class<T> entityClass, Object... params);
}
