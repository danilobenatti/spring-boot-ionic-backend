--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    nome character varying(80) NOT NULL
);



--
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.categoria_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
--

CREATE TABLE public.cidade (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    estado_id integer NOT NULL
);



--
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.cidade_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.cidade_id_seq OWNED BY public.cidade.id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    cpf_ou_cnpj character varying(20) NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(50) NOT NULL,
    tipo integer NOT NULL
);



--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- Name: endereco; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.endereco (
    id integer NOT NULL,
    bairro character varying(25),
    cep character varying(10) NOT NULL,
    complemento character varying(15),
    logradouro character varying(255) NOT NULL,
    numero character varying(15) NOT NULL,
    cidade_id integer NOT NULL,
    cliente_id integer NOT NULL
);



--
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.endereco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.id;


--
-- Name: estado; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.estado (
    id integer NOT NULL,
    nome character varying(20) NOT NULL,
    sigla character varying(2) NOT NULL
);



--
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.estado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.estado_id_seq OWNED BY public.estado.id;


--
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.item_pedido (
    desconto double precision DEFAULT 0 NOT NULL,
    preco double precision NOT NULL,
    quantidade integer NOT NULL,
    pedido_id integer NOT NULL,
    produto_id integer NOT NULL,
    CONSTRAINT item_pedido_quantidade_check CHECK ((quantidade >= 1))
);



--
-- Name: pagamento; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.pagamento (
    pedido_id integer NOT NULL,
    status integer NOT NULL
);



--
-- Name: pagamento_com_boleto; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.pagamento_com_boleto (
    data_pagamento timestamp without time zone,
    data_vencimento timestamp without time zone NOT NULL,
    pedido_id integer NOT NULL
);



--
-- Name: pagamento_com_cartao; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.pagamento_com_cartao (
    numero_de_parcelas integer NOT NULL,
    pedido_id integer NOT NULL,
    CONSTRAINT pagamento_com_cartao_numero_de_parcelas_check CHECK ((numero_de_parcelas >= 1))
);



--
-- Name: pedido; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.pedido (
    id integer NOT NULL,
    instante timestamp without time zone NOT NULL,
    cliente_id integer NOT NULL,
    endereco_entrega_id integer NOT NULL
);



--
-- Name: pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.pedido_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.pedido_id_seq OWNED BY public.pedido.id;


--
-- Name: produto; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.produto (
    id integer NOT NULL,
    nome character varying(150) NOT NULL,
    preco double precision DEFAULT 0 NOT NULL
);



--
-- Name: produto_categoria; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.produto_categoria (
    produto_id integer NOT NULL,
    categoria_id integer NOT NULL
);



--
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.produto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;


--
-- Name: telefone; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.telefone (
    cliente_id integer NOT NULL,
    numero character varying(20) NOT NULL
);



--
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- Name: cidade id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cidade ALTER COLUMN id SET DEFAULT nextval('public.cidade_id_seq'::regclass);


--
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- Name: endereco id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);


--
-- Name: estado id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.estado ALTER COLUMN id SET DEFAULT nextval('public.estado_id_seq'::regclass);


--
-- Name: pedido id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pedido ALTER COLUMN id SET DEFAULT nextval('public.pedido_id_seq'::regclass);


--
-- Name: produto id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: cidade cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: estado estado_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- Name: item_pedido item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (pedido_id, produto_id);


--
-- Name: pagamento_com_boleto pagamento_com_boleto_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pagamento_com_boleto
    ADD CONSTRAINT pagamento_com_boleto_pkey PRIMARY KEY (pedido_id);


--
-- Name: pagamento_com_cartao pagamento_com_cartao_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pagamento_com_cartao
    ADD CONSTRAINT pagamento_com_cartao_pkey PRIMARY KEY (pedido_id);


--
-- Name: pagamento pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (pedido_id);


--
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id);


--
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- Name: telefone telefone_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT telefone_pkey PRIMARY KEY (cliente_id, numero);


--
-- Name: categoria uk_categoria__nome; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT uk_categoria__nome UNIQUE (nome);


--
-- Name: cliente uk_cliente__email; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT uk_cliente__email UNIQUE (email);


--
-- Name: produto uk_produto__nome; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT uk_produto__nome UNIQUE (nome);


--
-- Name: produto_categoria uk_produtoid_categoriaid; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.produto_categoria
    ADD CONSTRAINT uk_produtoid_categoriaid UNIQUE (produto_id, categoria_id);


--
-- Name: telefone uk_telefone__numero; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT uk_telefone__numero UNIQUE (numero);


--
-- Name: cidade fk_cidade__estado_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT fk_cidade__estado_id FOREIGN KEY (estado_id) REFERENCES public.estado(id) ON DELETE CASCADE;


--
-- Name: endereco fk_endereco__cidade_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT fk_endereco__cidade_id FOREIGN KEY (cidade_id) REFERENCES public.cidade(id) ON DELETE RESTRICT;


--
-- Name: endereco fk_endereco__cliente_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT fk_endereco__cliente_id FOREIGN KEY (cliente_id) REFERENCES public.cliente(id) ON DELETE CASCADE;


--
-- Name: item_pedido fk_itempedido__pedido_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT fk_itempedido__pedido_id FOREIGN KEY (pedido_id) REFERENCES public.pedido(id) ON DELETE CASCADE;


--
-- Name: item_pedido fk_itempedido__produto_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT fk_itempedido__produto_id FOREIGN KEY (produto_id) REFERENCES public.produto(id) ON DELETE CASCADE;


--
-- Name: pagamento fk_pagamento__pedido_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pagamento__pedido_id FOREIGN KEY (pedido_id) REFERENCES public.pedido(id) ON DELETE CASCADE;


--
-- Name: pedido fk_pedido__cliente_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk_pedido__cliente_id FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- Name: pedido fk_pedido__enderecoentrega_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk_pedido__enderecoentrega_id FOREIGN KEY (endereco_entrega_id) REFERENCES public.endereco(id) ON DELETE CASCADE;


--
-- Name: produto_categoria fk_produto__categoria_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.produto_categoria
    ADD CONSTRAINT fk_produto__categoria_id FOREIGN KEY (categoria_id) REFERENCES public.categoria(id) ON DELETE RESTRICT;


--
-- Name: produto_categoria fk_produto__produto_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.produto_categoria
    ADD CONSTRAINT fk_produto__produto_id FOREIGN KEY (produto_id) REFERENCES public.produto(id) ON DELETE RESTRICT;


--
-- Name: telefone fk_telefone__cliente_id; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone__cliente_id FOREIGN KEY (cliente_id) REFERENCES public.cliente(id) ON DELETE CASCADE;


--
-- Name: pagamento_com_boleto fkcr74vrxf8nfph0knq2bho8doo; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pagamento_com_boleto
    ADD CONSTRAINT fk_pagamento_com_boleto__pedido_id FOREIGN KEY (pedido_id) REFERENCES public.pagamento(pedido_id);


--
-- Name: pagamento_com_cartao fkta3cdnuuxclwfh52t4qi432ow; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pagamento_com_cartao
    ADD CONSTRAINT fk_pagamento_com_cartao__pedido_id FOREIGN KEY (pedido_id) REFERENCES public.pagamento(pedido_id);


--
-- PostgreSQL database dump complete
--

