-- danilonb_mysql-springboot_ionic.categoria definition

CREATE TABLE categoria (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(80) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_categoria__nome (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.cliente definition

CREATE TABLE cliente (
  id int NOT NULL AUTO_INCREMENT,
  cpf_ou_cnpj varchar(20) NOT NULL,
  email varchar(255) NOT NULL,
  nome varchar(50) NOT NULL,
  tipo int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_cliente__email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.estado definition

CREATE TABLE estado (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(20) NOT NULL,
  sigla varchar(2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.produto definition

CREATE TABLE produto (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(150) NOT NULL,
  preco double NOT NULL DEFAULT '0',
  PRIMARY KEY (id),
  UNIQUE KEY uk_produto__nome (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.cidade definition

CREATE TABLE cidade (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(30) NOT NULL,
  estado_id int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_cidade__estado_id (estado_id),
  CONSTRAINT fk_cidade__estado_id FOREIGN KEY (estado_id) REFERENCES estado (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.endereco definition

CREATE TABLE endereco (
  id int NOT NULL AUTO_INCREMENT,
  bairro varchar(25) DEFAULT NULL,
  cep varchar(10) NOT NULL,
  complemento varchar(15) DEFAULT NULL,
  logradouro varchar(255) NOT NULL,
  numero varchar(15) NOT NULL,
  cidade_id int NOT NULL,
  cliente_id int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_endereco__cidade_id (cidade_id),
  KEY fk_endereco__cliente_id (cliente_id),
  CONSTRAINT fk_endereco__cidade_id FOREIGN KEY (cidade_id) REFERENCES cidade (id) ON DELETE RESTRICT,
  CONSTRAINT fk_endereco__cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.pedido definition

CREATE TABLE pedido (
  id int NOT NULL AUTO_INCREMENT,
  instante datetime(6) NOT NULL,
  cliente_id int NOT NULL,
  endereco_entrega_id int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_pedido__cliente_id (cliente_id),
  KEY fk_pedido__enderecoentrega_id (endereco_entrega_id),
  CONSTRAINT fk_pedido__cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id),
  CONSTRAINT fk_pedido__enderecoentrega_id FOREIGN KEY (endereco_entrega_id) REFERENCES endereco (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.produto_categoria definition

CREATE TABLE produto_categoria (
  produto_id int NOT NULL,
  categoria_id int NOT NULL,
  UNIQUE KEY uk_produtoid_categoriaid (produto_id,categoria_id),
  KEY fk_produto__categoria_id (categoria_id),
  CONSTRAINT fk_produto__categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE RESTRICT,
  CONSTRAINT fk_produto__produto_id FOREIGN KEY (produto_id) REFERENCES produto (id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.telefone definition

CREATE TABLE telefone (
  cliente_id int NOT NULL,
  numero varchar(20) NOT NULL,
  PRIMARY KEY (cliente_id,numero),
  UNIQUE KEY uk_telefone__numero (numero),
  CONSTRAINT fk_telefone__cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.item_pedido definition

CREATE TABLE item_pedido (
  desconto double NOT NULL DEFAULT '0',
  preco double NOT NULL,
  quantidade int NOT NULL,
  pedido_id int NOT NULL,
  produto_id int NOT NULL,
  PRIMARY KEY (pedido_id,produto_id),
  KEY fk_itempedido__produto_id (produto_id),
  CONSTRAINT fk_itempedido__pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido (id) ON DELETE CASCADE,
  CONSTRAINT fk_itempedido__produto_id FOREIGN KEY (produto_id) REFERENCES produto (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.pagamento definition

CREATE TABLE pagamento (
  pedido_id int NOT NULL,
  status int NOT NULL,
  PRIMARY KEY (pedido_id),
  CONSTRAINT fk_pagamento__pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.pagamento_com_boleto definition

CREATE TABLE pagamento_com_boleto (
  data_pagamento datetime(6) DEFAULT NULL,
  data_vencimento datetime(6) NOT NULL,
  pedido_id int NOT NULL,
  PRIMARY KEY (pedido_id),
  CONSTRAINT fk_pagamento_com_boleto__pedido_id FOREIGN KEY (pedido_id) REFERENCES pagamento (pedido_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- danilonb_mysql-springboot_ionic.pagamento_com_cartao definition

CREATE TABLE pagamento_com_cartao (
  numero_de_parcelas int NOT NULL,
  pedido_id int NOT NULL,
  PRIMARY KEY (pedido_id),
  CONSTRAINT fk_pagamento_com_cartao__pedido_id FOREIGN KEY (pedido_id) REFERENCES pagamento (pedido_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;