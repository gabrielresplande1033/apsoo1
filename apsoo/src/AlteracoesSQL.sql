/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  gabriel
 * Created: 25/06/2018
 */



/******TABELA USUARIO*******/
CREATE TABLE public.usuario
(
  id_serial integer NOT NULL DEFAULT nextval('usuario_id_serial_seq'::regclass),
  nome_usuario character varying NOT NULL,
  email_usuario character varying NOT NULL,
  login_usuario character varying NOT NULL,
  cpf_usuario character varying NOT NULL,
  senha_usuario character varying NOT NULL,
  CONSTRAINT chave_id PRIMARY KEY (id_serial)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;

-- Table: public.cliente

-- DROP TABLE public.cliente;

CREATE TABLE public.cliente
(
  nome_cliente character varying NOT NULL,
  cpf_cliente character varying NOT NULL,
  telefone_cliente character varying,
  idade_cliente integer,
  email_cliente character varying NOT NULL,
  CONSTRAINT cliente_pkey PRIMARY KEY (cpf_cliente)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cliente
  OWNER TO postgres;

CREATE TABLE public.produto
(
  cod_barras integer NOT NULL,
  nome_produto character varying NOT NULL,
  descricao_produto character varying NOT NULL,
  fabricante_produto character varying NOT NULL,
  valor_unitario_produto integer NOT NULL,
  categoria_produto character varying NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.produto
  OWNER TO postgres;


-- Table: public.formapagamento

-- DROP TABLE public.formapagamento;

CREATE TABLE public.formapagamento
(
  id_forma_pagamento integer NOT NULL DEFAULT nextval('formapagamento_id_forma_pagamento_seq'::regclass),
  nome_forma_pagamento character varying NOT NULL,
  CONSTRAINT chave_pagamento PRIMARY KEY (id_forma_pagamento)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.formapagamento
  OWNER TO postgres

-- Table: public.itens_venda

-- DROP TABLE public.itens_venda;

CREATE TABLE public.itens_venda
(
  id_item_venda integer NOT NULL DEFAULT nextval('itens_venda_id_item_venda_seq'::regclass),
  id_venda integer NOT NULL,
  cod_barras integer NOT NULL,
  CONSTRAINT chave_item_venda PRIMARY KEY (id_item_venda),
  CONSTRAINT chave_es_produto FOREIGN KEY (cod_barras)
      REFERENCES public.produto (cod_barras) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT chave_es_venda FOREIGN KEY (id_venda)
      REFERENCES public.venda_produto (id_venda) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.itens_venda
  OWNER TO postgres;


-- Table: public.venda_produto

-- DROP TABLE public.venda_produto;

CREATE TABLE public.venda_produto
(
  id_venda integer NOT NULL DEFAULT nextval('venda_produto_id_venda_seq'::regclass),
  cliente_cpf character varying NOT NULL,
  forma_pagamento_id integer NOT NULL,
  data_venda character varying NOT NULL,
  valor_total money,
  CONSTRAINT chave_venda PRIMARY KEY (id_venda),
  CONSTRAINT chave_es_cliente FOREIGN KEY (cliente_cpf)
      REFERENCES public.cliente (cpf_cliente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT chave_pagamento FOREIGN KEY (forma_pagamento_id)
      REFERENCES public.formapagamento (id_forma_pagamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.venda_produto
  OWNER TO postgres;


Popular Banco




INSERT INTO produto(cod_barras, nome_produto, descricao_produto, fabricante_produto, valor_unitario_produto, categoria_produto)
VALUES
(43248,'Boneco Max Steel','Boneco com itens do max steel','DORI','70','Boneco'),
(84575,'Beybleid','Peão para rodar beybleid','Hihappy','40','Peão'),
(42194,'Boneca Barbie','Boneca Barbie','Hihappy','120','Boneca'),
(48210,'Bola de Futebol','Bola de Futebol de salão','Adidas','60','Bola'),
(47320,'Bola de Volei','Bola para jogar Volei','Topper','30','Bola');



INSERT INTO formapagamento(nome_forma_pagamento)
VALUES
('Dinheiro'),
('Cartão de Crédito'),
('Boleto'),
('Cartão de Débito');

INSERT INTO cliente(nome_cliente, cpf_cliente, telefone_cliente, idade_cliente, email_cliente)
VALUES
('Jose Augusto Silva',42198409149,'42198491281',44, 'Jos@gmail.com'),
('Marcia Fabiana', 21948219423,'4483984933',33,'mamarcia@gmail.com'),
('Fernanda Gomes', 41298491289, '21401274810274', 18, 'fe@gmail.com');

