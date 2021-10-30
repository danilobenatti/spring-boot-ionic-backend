-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: danilonb_mysql-springboot_ionic
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

SET foreign_key_checks = 0;
DELETE FROM categoria;
DELETE FROM cidade;
DELETE FROM cliente;
DELETE FROM endereco;
DELETE FROM estado;
DELETE FROM item_pedido;
DELETE FROM pagamento;
DELETE FROM pagamento_com_boleto;
DELETE FROM pagamento_com_cartao;
DELETE FROM pedido;
DELETE FROM produto;
DELETE FROM produto_categoria;
DELETE FROM telefone;
SET foreign_key_checks = 1;

ALTER TABLE categoria AUTO_INCREMENT = 1;
ALTER TABLE cidade AUTO_INCREMENT = 1;
ALTER TABLE cliente AUTO_INCREMENT = 1;
ALTER TABLE endereco AUTO_INCREMENT = 1;
ALTER TABLE estado AUTO_INCREMENT = 1;
ALTER TABLE item_pedido AUTO_INCREMENT = 1;
ALTER TABLE pagamento AUTO_INCREMENT = 1;
ALTER TABLE pagamento_com_boleto AUTO_INCREMENT = 1;
ALTER TABLE pagamento_com_cartao AUTO_INCREMENT = 1;
ALTER TABLE pedido AUTO_INCREMENT = 1;
ALTER TABLE produto AUTO_INCREMENT = 1;
ALTER TABLE produto_categoria AUTO_INCREMENT = 1;
ALTER TABLE telefone AUTO_INCREMENT = 1;

--
-- Dumping data for table categoria
--

LOCK TABLES categoria WRITE;
/*!40000 ALTER TABLE categoria DISABLE KEYS */;
INSERT  IGNORE INTO categoria (id, nome) VALUES (1,'Informática'),(2,'Escritório'),(3,'Cama, mesa e banho'),(4,'Eletrônicos'),(5,'Decoração'),(6,'Jardinagem'),(7,'Perfumaria');
/*!40000 ALTER TABLE categoria ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table produto
--

LOCK TABLES produto WRITE;
/*!40000 ALTER TABLE produto DISABLE KEYS */;
INSERT  IGNORE INTO produto (id, nome, preco) VALUES (1,'Computador',2000),(2,'Impressora',800),(3,'Mouse',80),(4,'Mesa de Escritório',300),(5,'Toalha',50),(6,'Colcha',200),(7,'TV True Color',1200),(8,'Roçadeira',800),(9,'Abajour',100),(10,'Luminária Pendente',180),(11,'Shampoo',90);
/*!40000 ALTER TABLE produto ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table produto_categoria
--

LOCK TABLES produto_categoria WRITE;
/*!40000 ALTER TABLE produto_categoria DISABLE KEYS */;
INSERT  IGNORE INTO produto_categoria (produto_id, categoria_id) VALUES (1,1),(2,1),(3,1),(2,2),(4,2),(5,3),(6,3),(1,4),(2,4),(3,4),(7,4),(8,5),(9,6),(10,6),(11,7);
/*!40000 ALTER TABLE produto_categoria ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table estado
--

LOCK TABLES estado WRITE;
/*!40000 ALTER TABLE estado DISABLE KEYS */;
INSERT  IGNORE INTO estado (id, nome, sigla) VALUES (1,'Minas Gerais','MG'),(2,'São Paulo','SP');
/*!40000 ALTER TABLE estado ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table cidade
--

LOCK TABLES cidade WRITE;
/*!40000 ALTER TABLE cidade DISABLE KEYS */;
INSERT  IGNORE INTO cidade (id, nome, estado_id) VALUES (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);
/*!40000 ALTER TABLE cidade ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table cliente
--

LOCK TABLES cliente WRITE;
/*!40000 ALTER TABLE cliente DISABLE KEYS */;
INSERT  IGNORE INTO cliente (id, cpf_ou_cnpj, email, nome, tipo) VALUES (1,'363262161-00','maria@gmail.com','Maria Silva',1);
/*!40000 ALTER TABLE cliente ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table endereco
--

LOCK TABLES endereco WRITE;
/*!40000 ALTER TABLE endereco DISABLE KEYS */;
INSERT  IGNORE INTO endereco (id, bairro, cep, complemento, logradouro, numero, cidade_id, cliente_id) VALUES (1,'Jardim','38302000','apt 203','Rua Flores','300',1,1),(2,'Centro','38777012','sala 800','Avenida Matos','105',2,1);
/*!40000 ALTER TABLE endereco ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table telefone
--

LOCK TABLES telefone WRITE;
/*!40000 ALTER TABLE telefone DISABLE KEYS */;
INSERT  IGNORE INTO telefone (cliente_id, numero) VALUES (1,'98889696'),(1,'22223333');
/*!40000 ALTER TABLE telefone ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table pedido
--

LOCK TABLES pedido WRITE;
/*!40000 ALTER TABLE pedido DISABLE KEYS */;
INSERT  IGNORE INTO pedido (id, instante, cliente_id, endereco_entrega_id) VALUES (1,'2017-09-30 13:32:00.000000',1,1),(2,'2017-10-10 22:35:00.000000',1,2);
/*!40000 ALTER TABLE pedido ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table item_pedido
--

LOCK TABLES item_pedido WRITE;
/*!40000 ALTER TABLE item_pedido DISABLE KEYS */;
INSERT  IGNORE INTO item_pedido (desconto, preco, quantidade, pedido_id, produto_id) VALUES (0,2000,1,1,1),(0,80,2,1,3),(100,800,1,2,2);
/*!40000 ALTER TABLE item_pedido ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table pagamento
--

LOCK TABLES pagamento WRITE;
/*!40000 ALTER TABLE pagamento DISABLE KEYS */;
INSERT  IGNORE INTO pagamento (pedido_id, status) VALUES (1,2),(2,1);
/*!40000 ALTER TABLE pagamento ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table pagamento_com_boleto
--

LOCK TABLES pagamento_com_boleto WRITE;
/*!40000 ALTER TABLE pagamento_com_boleto DISABLE KEYS */;
INSERT  IGNORE INTO pagamento_com_boleto (data_pagamento, data_vencimento, pedido_id) VALUES (NULL,'2017-10-20 02:00:00.000000',2);
/*!40000 ALTER TABLE pagamento_com_boleto ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table pagamento_com_cartao
--

LOCK TABLES pagamento_com_cartao WRITE;
/*!40000 ALTER TABLE pagamento_com_cartao DISABLE KEYS */;
INSERT  IGNORE INTO pagamento_com_cartao (numero_de_parcelas, pedido_id) VALUES (6,1);
/*!40000 ALTER TABLE pagamento_com_cartao ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 23:42:02
