package com.rest.api.jdbcjpa.jdbc.entity;

import com.rest.api.jdbcjpa.jdbc.annotations.Table;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "pessoa_jdbc_entity")
public class PessoaJdbcEntity extends Entity {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String sobrenome;
    private Date dataNascimento;
}
