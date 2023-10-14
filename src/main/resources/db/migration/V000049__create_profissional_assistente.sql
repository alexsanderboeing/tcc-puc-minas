CREATE TABLE profissional_assistente (
    id           NUMBER(18) NOT NULL,
    operadora_id NUMBER(18) NOT NULL,
    pre_cadastro NUMBER(1),
    observacao   VARCHAR2(500 CHAR),
    nome         VARCHAR2(120 CHAR),
    created_at   DATE,
    created_by   NUMBER(18),
    updated_at   DATE,
    updated_by   NUMBER(18)
);

ALTER TABLE profissional_assistente ADD CONSTRAINT profissional_assistente_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_profissional_assistente
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
