package com.rest.api.jdbcjpa.jdbc.dao;

import com.rest.api.jdbcjpa.jdbc.entity.PessoaJdbcEntity;

import java.io.Serializable;

public interface PessoaJdbcDAO extends Serializable {

    PessoaJdbcEntity buscarPessoa();
}
