CREATE TABLE contrato_aditivo (
    id          NUMBER(18) NOT NULL,
    contrato_id NUMBER(18) NOT NULL,
    data        DATE,
    observacao  VARCHAR2(500 CHAR),
    created_at  DATE,
    created_by  NUMBER(18),
    updated_at  DATE,
    updated_by  NUMBER(18)
);

ALTER TABLE contrato_aditivo ADD CONSTRAINT contrato_aditivo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_contrato_aditivo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
