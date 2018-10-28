package com.rest.api.jdbcjpa.jdbc.dao;

import com.rest.api.jdbcjpa.jdbc.dbconfig.DataSource;
import com.rest.api.jdbcjpa.jdbc.entity.PessoaJdbcEntity;

public class PessoaJdbcDAOImpl extends DAO<PessoaJdbcEntity> implements PessoaJdbcDAO {

    public PessoaJdbcDAOImpl() {
        super(DataSource.get("main:datasource.properties", "baseMemoria"));
    }

    public PessoaJdbcDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public PessoaJdbcEntity buscarPessoa() {
        return this.findById(1L);
    }
}
