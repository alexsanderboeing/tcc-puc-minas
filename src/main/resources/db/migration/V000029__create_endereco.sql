CREATE TABLE endereco (
    id                         NUMBER(18) NOT NULL,
    cep_endereco               NUMBER(10),
    logradouro_endereco        VARCHAR2(100 CHAR),
    numero_endereco            VARCHAR2(5 CHAR),
    complemento_endereco       VARCHAR2(30 CHAR),
    bairro_endereco            VARCHAR2(40 CHAR),
    cidade_endereco            VARCHAR2(72 CHAR),
    uf_endereco                VARCHAR2(2 CHAR),
    profissional_assistente_id NUMBER(18) NOT NULL,
    operadora_id               NUMBER(18) NOT NULL,
    beneficiario_id            NUMBER(18) NOT NULL,
    created_at                 DATE,
    created_by                 NUMBER(18),
    updated_at                 DATE,
    updated_by                 NUMBER(18)
);

ALTER TABLE endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_endereco
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
