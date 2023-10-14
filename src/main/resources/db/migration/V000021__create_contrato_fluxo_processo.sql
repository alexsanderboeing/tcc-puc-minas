CREATE TABLE contrato_fluxo_processo (
    id                NUMBER(18) NOT NULL,
    fluxo_processo_id NUMBER(18) NOT NULL,
    contrato_id       NUMBER(18) NOT NULL,
    preve_sla         NUMBER(1),
    tempo_em_horas    NUMBER(4),
    calendario_id     NUMBER(18) NOT NULL,
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE contrato_fluxo_processo ADD CONSTRAINT contrato_fluxo_processo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_contrato_fluxo_processo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
