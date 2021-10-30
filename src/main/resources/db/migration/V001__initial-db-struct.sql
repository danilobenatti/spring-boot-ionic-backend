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

--
-- Table structure for table categoria
--

DROP TABLE IF EXISTS categoria;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE categoria (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table cidade
--

DROP TABLE IF EXISTS cidade;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cidade (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(30) DEFAULT NULL,
  estado_id int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_cidade__estado_id (estado_id),
  CONSTRAINT fk_cidade__estado_id FOREIGN KEY (estado_id) REFERENCES estado (id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table cliente
--

DROP TABLE IF EXISTS cliente;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cliente (
  id int NOT NULL AUTO_INCREMENT,
  cpf_ou_cnpj varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  nome varchar(50) DEFAULT NULL,
  tipo int DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_cmxo70m08n43599l3h0h07cc6 (email)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table endereco
--

DROP TABLE IF EXISTS endereco;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE endereco (
  id int NOT NULL AUTO_INCREMENT,
  bairro varchar(255) DEFAULT NULL,
  cep varchar(255) DEFAULT NULL,
  complemento varchar(255) DEFAULT NULL,
  logradouro varchar(255) DEFAULT NULL,
  numero varchar(255) DEFAULT NULL,
  cidade_id int NOT NULL,
  cliente_id int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_endereco__cidade_id (cidade_id),
  KEY fk_endereco__cliente_id (cliente_id),
  CONSTRAINT fk_endereco__cidade_id FOREIGN KEY (cidade_id) REFERENCES cidade (id) ON DELETE CASCADE,
  CONSTRAINT fk_endereco__cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table estado
--

DROP TABLE IF EXISTS estado;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE estado (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(20) DEFAULT NULL,
  sigla varchar(2) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table item_pedido
--

DROP TABLE IF EXISTS item_pedido;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE item_pedido (
  desconto double DEFAULT NULL,
  preco double DEFAULT NULL,
  quantidade int DEFAULT NULL,
  pedido_id int NOT NULL,
  produto_id int NOT NULL,
  PRIMARY KEY (pedido_id,produto_id),
  KEY fk_itempedido__produto_id (produto_id),
  CONSTRAINT fk_itempedido__pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido (id) ON DELETE CASCADE,
  CONSTRAINT fk_itempedido__produto_id FOREIGN KEY (produto_id) REFERENCES produto (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table pagamento
--

DROP TABLE IF EXISTS pagamento;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pagamento (
  pedido_id int NOT NULL,
  status int DEFAULT NULL,
  PRIMARY KEY (pedido_id),
  CONSTRAINT fk_pagamento_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table pagamento_com_boleto
--

DROP TABLE IF EXISTS pagamento_com_boleto;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pagamento_com_boleto (
  data_pagamento datetime(6) DEFAULT NULL,
  data_vencimento datetime(6) DEFAULT NULL,
  pedido_id int NOT NULL,
  PRIMARY KEY (pedido_id),
  CONSTRAINT FKcr74vrxf8nfph0knq2bho8doo FOREIGN KEY (pedido_id) REFERENCES pagamento (pedido_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table pagamento_com_cartao
--

DROP TABLE IF EXISTS pagamento_com_cartao;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pagamento_com_cartao (
  numero_de_parcelas int DEFAULT NULL,
  pedido_id int NOT NULL,
  PRIMARY KEY (pedido_id),
  CONSTRAINT FKta3cdnuuxclwfh52t4qi432ow FOREIGN KEY (pedido_id) REFERENCES pagamento (pedido_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table pedido
--

DROP TABLE IF EXISTS pedido;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedido (
  id int NOT NULL AUTO_INCREMENT,
  instante datetime(6) DEFAULT NULL,
  cliente_id int NOT NULL,
  endereco_entrega_id int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_pedido_cliente_id (cliente_id),
  KEY fk_pedido__enderecoentrega_id (endereco_entrega_id),
  CONSTRAINT fk_pedido__enderecoentrega_id FOREIGN KEY (endereco_entrega_id) REFERENCES endereco (id) ON DELETE CASCADE,
  CONSTRAINT fk_pedido_cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table produto
--

DROP TABLE IF EXISTS produto;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE produto (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  preco double DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table produto_categoria
--

DROP TABLE IF EXISTS produto_categoria;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE produto_categoria (
  produto_id int NOT NULL,
  categoria_id int NOT NULL,
  UNIQUE KEY uk_produtoid_categoriaid (produto_id,categoria_id),
  KEY fk_produto__categoria_id (categoria_id),
  CONSTRAINT fk_produto__categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE RESTRICT,
  CONSTRAINT fk_produto__produto_id FOREIGN KEY (produto_id) REFERENCES produto (id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table telefone
--

DROP TABLE IF EXISTS telefone;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE telefone (
  cliente_id int NOT NULL,
  numero varchar(255) DEFAULT NULL,
  UNIQUE KEY uk_telefone__numero (numero),
  KEY fk_telefone__cliente_id (cliente_id),
  CONSTRAINT fk_telefone__cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 21:25:34
