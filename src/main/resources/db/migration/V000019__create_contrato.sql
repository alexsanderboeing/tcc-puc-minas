CREATE TABLE contrato (
    id                    NUMBER(18) NOT NULL,
    operadora_id          NUMBER(18) NOT NULL,
    vigencia              DATE,
    mes_aniversario       NUMBER(2),
    dm_indice_reajuste_id NUMBER(10) NOT NULL,
    data_cancelamento     DATE,
    created_at            DATE,
    created_by            NUMBER(18),
    updated_at            DATE,
    updated_by            NUMBER(18)
);

ALTER TABLE contrato ADD CONSTRAINT contrato_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_contrato
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
