CREATE TABLE processo_anexo (
    id          NUMBER(18) NOT NULL,
    arquivo_id  NUMBER(18) NOT NULL,
    processo_id NUMBER(18) NOT NULL,
    created_at  DATE,
    created_by  NUMBER(18),
    updated_at  DATE,
    updated_by  NUMBER(18)
);

ALTER TABLE processo_anexo ADD CONSTRAINT processo_anexo_pk PRIMARY KEY ( id );

ALTER TABLE processo_anexo ADD CONSTRAINT processo_anexo_uk UNIQUE ( arquivo_id, processo_id );

CREATE SEQUENCE s_processo_anexo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
