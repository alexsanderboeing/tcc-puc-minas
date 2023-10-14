CREATE TABLE acao (
    id                NUMBER(18) NOT NULL,
    nome              VARCHAR2(70 CHAR),
    nome_botao        VARCHAR2(70 CHAR),
    cor_botao         VARCHAR2(10 CHAR),
    ordem_em_tela     NUMBER(4),
    dm_modelo_tela_id NUMBER(10),
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE acao ADD CONSTRAINT acao_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_acao
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
