CREATE TABLE classificacao_interna (
    id         NUMBER NOT NULL,
    nome       VARCHAR2(70 CHAR),
    ativo      NUMBER(1),
    created_at DATE,
    created_by NUMBER(18),
    updated_at DATE,
    updated_by NUMBER(18)
);

ALTER TABLE classificacao_interna ADD CONSTRAINT classificacao_interna_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_classificacao_interna
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
