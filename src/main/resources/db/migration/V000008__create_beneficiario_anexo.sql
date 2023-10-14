CREATE TABLE beneficiario_anexo (
    id              NUMBER(18) NOT NULL,
    anexo_id        NUMBER(18) NOT NULL,
    beneficiario_id NUMBER(18) NOT NULL,
    created_at      DATE,
    created_by      NUMBER(18),
    updated_at      DATE,
    updated_by      NUMBER(18)
);

ALTER TABLE beneficiario_anexo ADD CONSTRAINT beneficiario_anexo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_beneficiario_anexo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
