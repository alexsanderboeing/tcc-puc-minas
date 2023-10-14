CREATE TABLE classificacao_anexo (
    id                 NUMBER(18) NOT NULL,
    descricao          VARCHAR2(70 CHAR),
    visivel_tela_anexo NUMBER(1),
    ativo              NUMBER(1),
    created_at         DATE,
    created_by         NUMBER(18),
    updated_at         DATE,
    updated_by         NUMBER(18)
);

ALTER TABLE classificacao_anexo ADD CONSTRAINT classificacao_anexo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_classificacao_anexo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
