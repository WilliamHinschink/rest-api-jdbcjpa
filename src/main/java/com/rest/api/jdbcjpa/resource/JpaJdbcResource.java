package com.rest.api.jdbcjpa.resource;

import com.rest.api.jdbcjpa.jdbc.dao.PessoaJdbcDAO;
import com.rest.api.jdbcjpa.jdbc.entity.PessoaJdbcEntity;
import com.rest.api.jdbcjpa.jpa.dao.PessoaJpaDAO;
import com.rest.api.jdbcjpa.jpa.entity.PessoaJpaEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@RequestScoped
@Path("teste/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JpaJdbcResource implements Serializable {

    private static final long serialVersionUID = -2727671384562903522L;

    @Inject
    private PessoaJpaDAO pessoaJpaDAO;

    @Inject
    private PessoaJdbcDAO pessoaJdbcDAO;

    @GET
    @Path("jpa/buscar-pessoa")
    public Response buscarPessoaJpa() {
        PessoaJpaEntity entity = this.pessoaJpaDAO.buscarPessoa();
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    @GET
    @Path("jdbc/buscar-pessoa")
    public Response buscarPessoaJdbc() {
        PessoaJdbcEntity entity = this.pessoaJdbcDAO.buscarPessoa();
        return Response.status(Response.Status.OK).entity(entity).build();
    }
}
