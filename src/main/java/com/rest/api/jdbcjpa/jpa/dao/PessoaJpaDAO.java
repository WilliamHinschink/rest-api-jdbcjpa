package com.rest.api.jdbcjpa.jpa.dao;

import com.rest.api.jdbcjpa.jpa.entity.PessoaJpaEntity;

import java.io.Serializable;

public interface PessoaJpaDAO extends Serializable {

    PessoaJpaEntity buscarPessoa();
}
