insert into categoria(id,nome) values (1,'Informática'),(2,'Escritório');
insert into produto(id,nome,preco) values (1,'Computador',2000.00),(2,'Impressora',800.00),(3,'Mouse',80.00);
insert into produto_categoria(categoria_id,produto_id) values (1,1),(1,2),(1,3),(2,2);
insert into estado(id,nome,sigla) values (1,'Minas Gerais','MG'),(2,'São Paulo','SP');
insert into cidade(id,nome,estado_id) values (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);