CREATE TABLE desempatador (
    id                NUMBER(18) NOT NULL,
    nome              VARCHAR2(120 CHAR),
    ativo             NUMBER(1),
    usuario_id        NUMBER(18),
    mini_curriculo    VARCHAR2(4000 CHAR),
    assinatura_base64 CLOB,
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE desempatador ADD CONSTRAINT desempatador_pk PRIMARY KEY ( id );


CREATE SEQUENCE s_desempatador
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
