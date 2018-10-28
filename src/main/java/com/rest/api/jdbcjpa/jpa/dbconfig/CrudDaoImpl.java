package com.rest.api.jdbcjpa.jpa.dbconfig;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

@Named("crudDao")
public class CrudDaoImpl<T, P> implements Serializable {

    private static final long serialVersionUID = -5140423592327646624L;

    @Inject
    protected BaseDaoImpl<T,P> dao;

    protected Class<T> entityClass;

    public Class<T> getEntityClass() {
        if (entityClass == null) {
            //only works if one extends BaseDao, we will take care of it with CDI
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }

    public T find(P id){
        return dao.find(id, getEntityClass());
    }

    public void delete(P id){
        dao.remove(id, getEntityClass());
    }

    public void update(P id){
        dao.update(id, getEntityClass());
    }

    public void insert(T t){
        dao.save(t);
    }

    public Collection<T> findAll(String instruction){
        return dao.findAll(instruction, getEntityClass());
    }
}
