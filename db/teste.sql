insert into cidade
values
  (default, 'Lajeado', 'RS', '1');
INSERT INTO `stockmanager`.`endereco` (
    ativo,
    bairro,
    cep,
    cidade_id,
    complementos,
    id,
    numero,
    rua
  )
VALUES
  (
    '1',
    'none',
    '999999',
    1,
    'wow',
    default,
    '666',
    'grove street'
  );
insert into pessoa
values
  (
    default,
    1,
    'Sr',
    'Junho',
    '666',
    null,
    '123',
    '123',
    '1'
  );
insert into funcionario
values(
    DEFAULT,
    1,
    'adm',
    md5('warlock'),
    'mestre dos magos',
    '1',
    'teste@testando.com',
    'dunno',
    '1'
  );
select
  *
from funcionario;
explain funcionario;