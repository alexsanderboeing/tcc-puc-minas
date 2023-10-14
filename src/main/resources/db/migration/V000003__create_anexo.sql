CREATE TABLE anexo (
    id                     NUMBER(18) NOT NULL,
    uuid                   VARCHAR2(36 CHAR),
    nome                   VARCHAR2(100 CHAR),
    classificacao_anexo_id NUMBER(18) NOT NULL,
    usuario_id             NUMBER(18),
    data_criacao           DATE,
    created_at             DATE,
    created_by             NUMBER(18),
    updated_at             DATE,
    updated_by             NUMBER(18)
);

ALTER TABLE anexo ADD CONSTRAINT arquivo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_anexo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
