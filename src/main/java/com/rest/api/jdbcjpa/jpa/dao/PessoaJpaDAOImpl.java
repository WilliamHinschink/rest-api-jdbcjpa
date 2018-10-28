package com.rest.api.jdbcjpa.jpa.dao;

import com.rest.api.jdbcjpa.jpa.dbconfig.CrudDaoImpl;
import com.rest.api.jdbcjpa.jpa.entity.PessoaJpaEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaJpaDAOImpl extends CrudDaoImpl<PessoaJpaEntity, Long> implements PessoaJpaDAO {

    @Override
    public PessoaJpaEntity buscarPessoa() {
        return this.find(1L);
    }
}
