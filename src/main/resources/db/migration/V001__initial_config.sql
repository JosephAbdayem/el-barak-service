CREATE SCHEMA IF NOT EXISTS elbarak;
-- ddl-end --
ALTER SCHEMA elbarak OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,elbarak;

-- object: elbarak.produto | type: TABLE --
-- DROP TABLE IF EXISTS elbarak.produto CASCADE;
CREATE TABLE IF NOT EXISTS elbarak.produto (
	id bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	descricao varchar(255),
	preco numeric(6,2) NOT NULL,
	ativo boolean DEFAULT true NOT NULL,
	categoria_id bigint NOT NULL,
	data_cadastro timestamp DEFAULT now() NOT NULL,
	data_atualizacao timestamp,
	CONSTRAINT produto_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE elbarak.produto OWNER TO postgres;
-- ddl-end --

-- object: elbarak.categoria | type: TABLE --
-- DROP TABLE IF EXISTS elbarak.categoria CASCADE;
CREATE TABLE IF NOT EXISTS elbarak.categoria (
	id bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	data_cadastro timestamp DEFAULT now() NOT NULL,
	data_atualizacao timestamp,
	ativo boolean DEFAULT true NOT NULL,
	CONSTRAINT categoria_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE elbarak.categoria OWNER TO postgres;
-- ddl-end --

-- object: elbarak.status | type: TABLE --
-- DROP TABLE IF EXISTS elbarak.status CASCADE;
CREATE TABLE IF NOT EXISTS elbarak.status (
	id bigserial NOT NULL,
	nome varchar(100) NOT NULL,
	data_cadastro timestamp DEFAULT now() NOT NULL,
	data_atualizacao timestamp,
	ativo boolean DEFAULT true NOT NULL,
	CONSTRAINT status_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE elbarak.status OWNER TO postgres;
-- ddl-end --

-- object: elbarak.usuario | type: TABLE --
-- DROP TABLE IF EXISTS elbarak.usuario CASCADE;
CREATE TABLE IF NOT EXISTS elbarak.usuario (
	id bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	login varchar(255),
	senha varchar(50),
	data_cadastro timestamp DEFAULT now() NOT NULL,
	data_atualizacao timestamp,
	ativo boolean DEFAULT true NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE elbarak.usuario OWNER TO postgres;
-- ddl-end --

-- object: elbarak.pedido | type: TABLE --
-- DROP TABLE IF EXISTS elbarak.pedido CASCADE;
CREATE TABLE IF NOT EXISTS elbarak.pedido (
	id bigserial NOT NULL,
	mesa numeric(4) NOT NULL,
	usuario_id bigint NOT NULL,
	status_id bigint NOT NULL,
	data_cadastro timestamp DEFAULT now() NOT NULL,
	data_atualizacao timestamp,
	ativo boolean DEFAULT true NOT NULL,
	CONSTRAINT pedido_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE elbarak.pedido OWNER TO postgres;
-- ddl-end --

-- object: elbarak.carrinho_pedido | type: TABLE --
-- DROP TABLE IF EXISTS elbarak.carrinho_pedido CASCADE;
CREATE TABLE IF NOT EXISTS elbarak.carrinho_pedido (
	pedido_id bigint NOT NULL,
	produto_id bigint NOT NULL,
	quantidade integer NOT NULL,
	CONSTRAINT carrinho_pedido_pk PRIMARY KEY (pedido_id,produto_id)

);
-- ddl-end --
ALTER TABLE elbarak.carrinho_pedido OWNER TO postgres;
-- ddl-end --

-- object: fk_categoria | type: CONSTRAINT --
-- ALTER TABLE elbarak.produto DROP CONSTRAINT IF EXISTS fk_categoria CASCADE;
ALTER TABLE elbarak.produto ADD CONSTRAINT fk_categoria_id FOREIGN KEY (categoria_id)
REFERENCES elbarak.categoria (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_usuario_id | type: CONSTRAINT --
-- ALTER TABLE elbarak.pedido DROP CONSTRAINT IF EXISTS fk_usuario_id CASCADE;
ALTER TABLE elbarak.pedido ADD CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id)
REFERENCES elbarak.usuario (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_status_id | type: CONSTRAINT --
-- ALTER TABLE elbarak.pedido DROP CONSTRAINT IF EXISTS fk_status_id CASCADE;
ALTER TABLE elbarak.pedido ADD CONSTRAINT fk_status_id FOREIGN KEY (status_id)
REFERENCES elbarak.status (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_pedido_id | type: CONSTRAINT --
-- ALTER TABLE elbarak.carrinho_pedido DROP CONSTRAINT IF EXISTS fk_pedido_id CASCADE;
ALTER TABLE elbarak.carrinho_pedido ADD CONSTRAINT fk_pedido_id FOREIGN KEY (pedido_id)
REFERENCES elbarak.pedido (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_produto_id | type: CONSTRAINT --
-- ALTER TABLE elbarak.carrinho_pedido DROP CONSTRAINT IF EXISTS fk_produto_id CASCADE;
ALTER TABLE elbarak.carrinho_pedido ADD CONSTRAINT fk_produto_id FOREIGN KEY (produto_id)
REFERENCES elbarak.produto (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --