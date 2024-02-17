create table servicos(

    id bigint not null auto_increment,
    servico varchar(100) not null,
    valor double(3,2) not null,
    duracao bigint not null,

    primary key(id)
);