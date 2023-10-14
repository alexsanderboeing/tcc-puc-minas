CREATE TABLE contrato_preco (
    id                  NUMBER(18) NOT NULL,
    contrato_id         NUMBER(18) NOT NULL,
    vigencia            DATE,
    valor               NUMBER(14, 2),
    percentual_reajuste NUMBER(14, 6),
    created_at          DATE,
    created_by          NUMBER(18),
    updated_at          DATE,
    updated_by          NUMBER(18)
);

ALTER TABLE contrato_preco ADD CONSTRAINT contrato_preco_pk PRIMARY KEY ( id );

ALTER TABLE contrato_preco ADD CONSTRAINT contrato_preco_uk UNIQUE ( contrato_id, vigencia );

CREATE SEQUENCE s_contrato_preco
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
