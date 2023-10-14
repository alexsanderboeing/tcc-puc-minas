CREATE TABLE parametro (
    id                     NUMBER NOT NULL,
    nome                   VARCHAR2(45 BYTE),
    dm_param_tipo_valor_id NUMBER(10) NOT NULL,
    nome_dominio           VARCHAR2(45 CHAR),
    valor_string           VARCHAR2(4000 CHAR),
    valor_date             DATE,
    valor_number           NUMBER,
    valor_clob             CLOB
);

ALTER TABLE parametro ADD CONSTRAINT parametro_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_parametro
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
