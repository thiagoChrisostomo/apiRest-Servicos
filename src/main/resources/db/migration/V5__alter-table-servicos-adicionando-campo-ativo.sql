ALTER TABLE servicos add ativo tinyint;
update servicos set ativo = 1;