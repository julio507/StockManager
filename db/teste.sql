insert into funcionario values( DEFAULT, 1, 'adm', md5('warlock'), 'mestre dos magos', '1','teste@testando.com', 'dunno', '1' );

insert into pessoa values ( default, 1, 'Sr', 'Junho', '666', null, '123', '123', '1' );

insert into endereco values ( default, 1,'unknown', 'unknown', '42', null , '123', NULL, '1' );

insert into cidade values ( default, 'Lajeado', 'RS', '1' ); 

select * from funcionario;

explain funcionario;