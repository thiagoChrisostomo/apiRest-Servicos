create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(60) not null,
    cpf varchar(11) not null,
    dataNascimento date,
    senha varchar(20) not null,
    logradouro varchar(100),
    numero varchar(10),
    complemento varchar(35),
    bairro varchar(45),
    cidade varchar(60),
    uf varchar(2),
    cep varchar(8),

    primary key(id)
);