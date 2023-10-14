CREATE TABLE profissional_assis_anexo (
    id                         NUMBER(18) NOT NULL,
    profissional_assistente_id NUMBER(18) NOT NULL,
    anexo_id                   NUMBER(18) NOT NULL,
    created_at                 DATE,
    created_by                 NUMBER(18),
    updated_at                 DATE,
    updated_by                 NUMBER(18)
);

ALTER TABLE profissional_assis_anexo ADD CONSTRAINT profissional_anexo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_profissional_assis_anexo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
