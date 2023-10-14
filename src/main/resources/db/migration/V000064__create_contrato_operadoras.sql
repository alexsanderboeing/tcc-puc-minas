CREATE TABLE contrato_operadoras (
    id           NUMBER NOT NULL,
    contrato_id  NUMBER(18) NOT NULL,
    operadora_id NUMBER(18) NOT NULL,
    created_at   DATE,
    created_by   NUMBER(18),
    updated_at   DATE,
    updated_by   NUMBER(18)
);

ALTER TABLE contrato_operadoras ADD CONSTRAINT contrato_operadoras_pk PRIMARY KEY ( id );

ALTER TABLE contrato_operadoras ADD CONSTRAINT contrato_operadoras_uk UNIQUE (contrato_id, operadora_id);

ALTER TABLE contrato_operadoras
    ADD CONSTRAINT contrato_operadoras_fk FOREIGN KEY ( contrato_id )
        REFERENCES contrato ( id );

ALTER TABLE contrato_operadoras
    ADD CONSTRAINT contrato_operadoras_fkv1 FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

CREATE SEQUENCE s_contrato_operadoras
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
