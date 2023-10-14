CREATE TABLE contato (
    id                         NUMBER(18) NOT NULL,
    dm_tipo_contato_id         NUMBER(10) NOT NULL,
    email                      VARCHAR2(100 CHAR),
    telefone                   VARCHAR2(20 CHAR),
    profissional_assistente_id NUMBER(18) NOT NULL,
    operadora_id               NUMBER(18) NOT NULL,
    beneficiario_id            NUMBER(18) NOT NULL,
    desempatador_id            NUMBER(18) NOT NULL,
    created_at                 DATE,
    created_by                 NUMBER(18),
    updated_at                 DATE,
    updated_by                 NUMBER(18)
);

ALTER TABLE contato ADD CONSTRAINT contato_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_contato
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
