create table agendamentos(

    id bigint not null auto_increment,
    funcionario_id bigint not null,
    servico_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_agendamentos_funcionario_id foreign key(funcionario_id) references funcionarios(id),
    constraint fk_agendamentos_servico_id foreign key(servico_id) references servicos(id)
);