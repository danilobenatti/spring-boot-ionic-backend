insert into categoria(id,nome) values (1,'Informática'),(2,'Escritório');
insert into produto(id,nome,preco) values (1,'Computador',2000.00),(2,'Impressora',800.00),(3,'Mouse',80.00);
insert into produto_categoria(categoria_id,produto_id) values (1,1),(1,2),(1,3),(2,2);
insert into estado(id,nome,sigla) values (1,'Minas Gerais','MG'),(2,'São Paulo','SP');
insert into cidade(id,nome,estado_id) values (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);
insert into cliente(id,nome,email,cpfoucnpj,tipo) values (1,'Maria Silva','maria@gmail.com','363262161-00',1);
insert into telefone(cliente_id,telefones) values (1,'98889696'),(1,'22223333');
insert into endereco(id,logradouro,numero,complemento,bairro,cep,cliente_id,cidade_id) values (1,'Rua Flores','300','apt 203','Jardim','38302000',1,1);
insert into endereco(id,logradouro,numero,complemento,bairro,cep,cliente_id,cidade_id) values (2,'Avenida Matos','105','sala 800','Centro','38777012',1,2);