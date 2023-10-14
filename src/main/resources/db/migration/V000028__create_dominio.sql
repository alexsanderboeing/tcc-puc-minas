CREATE TABLE dominio (
    id        NUMBER(10) NOT NULL,
    nome      VARCHAR2(45 CHAR),
    valor     VARCHAR2(45 CHAR),
    descricao VARCHAR2(120 CHAR)
);

ALTER TABLE dominio ADD CONSTRAINT dominio_pk PRIMARY KEY ( id );

ALTER TABLE dominio ADD CONSTRAINT dominio_uk UNIQUE (nome, valor);

CREATE SEQUENCE s_dominio
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
