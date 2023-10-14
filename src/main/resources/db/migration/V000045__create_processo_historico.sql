CREATE TABLE processo_historico (
    id                NUMBER(18) NOT NULL,
    processo_id       NUMBER(18) NOT NULL,
    processo_etapa_id NUMBER(18) NOT NULL,
    status_etapa_id   NUMBER(18) NOT NULL,
    data              DATE,
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE processo_historico ADD CONSTRAINT processo_historico_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_processo_historico
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
