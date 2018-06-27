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

INSERT INTO produto(cod_barras, nome_produto, descricao_produto, fabricante_produto, valor_unitario_produto, categoria_produto)
VALUES
(42194,'Boneca Barbie','Boneca Barbie','Hihappy','120','Boneca'),
(48210,'Bola de Futebol','Bola de Futebol de salão','Adidas','60','Bola'),
(47320,'Bola de Volei','Bola para jogar Volei','Topper','30','Bola');


/******TABELA USUARIO*******/