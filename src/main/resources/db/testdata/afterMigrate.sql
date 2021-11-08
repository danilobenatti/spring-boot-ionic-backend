-- Host: localhost    Database: danilonb_pgsql-springboot_ionic
-- ------------------------------------------------------

--SET foreign_key_checks = 0;
TRUNCATE TABLE categoria CASCADE;
TRUNCATE TABLE cidade CASCADE;
TRUNCATE TABLE cliente CASCADE;
TRUNCATE TABLE endereco CASCADE;
TRUNCATE TABLE estado CASCADE;
TRUNCATE TABLE item_pedido CASCADE;
TRUNCATE TABLE pagamento CASCADE;
TRUNCATE TABLE pagamento_com_boleto CASCADE;
TRUNCATE TABLE pagamento_com_cartao CASCADE;
TRUNCATE TABLE pedido CASCADE;
TRUNCATE TABLE produto CASCADE;
TRUNCATE TABLE produto_categoria CASCADE;
TRUNCATE TABLE telefone CASCADE;
--SET foreign_key_checks = 1;

--
-- Dumping data for table `categoria`
--

INSERT INTO categoria (id, nome) VALUES (1,'Informática'),(2,'Escritório'),(3,'Cama, mesa e banho'),(4,'Eletrônicos'),(5,'Decoração'),(6,'Jardinagem'),(7,'Perfumaria');
SELECT setval('categoria_id_seq', 7, true);
--
-- Dumping data for table `produto`
--

INSERT INTO produto (id, nome, preco) VALUES (1,'Computador',2000),(2,'Impressora',800),(3,'Mouse',80),(4,'Mesa de Escritório',300),(5,'Toalha',50),(6,'Colcha',200),(7,'TV True Color',1200),(8,'Roçadeira',800),(9,'Abajour',100),(10,'Luminária Pendente',180),(11,'Shampoo',90);
SELECT setval('produto_id_seq', 11, true);
--
-- Dumping data for table `produto_categoria`
--

INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (1,1),(2,1),(3,1),(2,2),(4,2),(5,3),(6,3),(1,4),(2,4),(3,4),(7,4),(8,5),(9,6),(10,6),(11,7);

--
-- Dumping data for table `estado`
--

INSERT INTO estado (id, nome, sigla) VALUES (1,'Minas Gerais','MG'),(2,'São Paulo','SP');
SELECT setval('estado_id_seq', 2, true);
--
-- Dumping data for table `cidade`
--

INSERT INTO cidade (id, nome, estado_id) VALUES (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);
SELECT setval('cidade_id_seq', 3, true);
--
-- Dumping data for table `cliente`
--

INSERT INTO cliente (id, cpf_ou_cnpj, email, nome, tipo) VALUES (1,'363262161-00','danilobenatti@gmail.com','Maria Silva',1);
SELECT setval('cliente_id_seq', 1, true);
--
-- Dumping data for table `endereco`
--

INSERT INTO endereco (id, bairro, cep, complemento, logradouro, numero, cidade_id, cliente_id) VALUES (1,'Jardim','38302000','apt 203','Rua Flores','300',1,1),(2,'Centro','38777012','sala 800','Avenida Matos','105',2,1);
SELECT setval('endereco_id_seq', 2, true);
--
-- Dumping data for table `telefone`
--

INSERT INTO telefone (cliente_id, numero) VALUES (1,'22223333'),(1,'98889696');

--
-- Dumping data for table `pedido`
--

INSERT INTO pedido (id, instante, cliente_id, endereco_entrega_id) VALUES (1,'2017-09-30 13:32:00',1,1),(2,'2017-10-10 22:35:00',1,2);
SELECT setval('pedido_id_seq', 2, true);
--
-- Dumping data for table `item_pedido`
--

INSERT INTO item_pedido (desconto, preco, quantidade, pedido_id, produto_id) VALUES (0,2000,1,1,1),(0,80,2,1,3),(100,800,1,2,2);

--
-- Dumping data for table `pagamento`
--

INSERT INTO pagamento (pedido_id, status) VALUES (1,2),(2,1);

--
-- Dumping data for table `pagamento_com_boleto`
--

INSERT INTO pagamento_com_boleto (data_pagamento, data_vencimento, pedido_id) VALUES (NULL,'2017-10-20 02:00:00',2);

--
-- Dumping data for table `pagamento_com_cartao`
--

INSERT INTO pagamento_com_cartao (numero_de_parcelas, pedido_id) VALUES (6,1);

