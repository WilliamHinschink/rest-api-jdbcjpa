CREATE TABLE pessoa_jdbc_entity (
   id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   sobrenome VARCHAR(50) NOT NULL,
   data_nascimento DATE NOT NULL
);