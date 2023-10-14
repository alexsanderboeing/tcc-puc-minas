CREATE TABLE processo_etapa (
    id                NUMBER(18) NOT NULL,
    fluxo_processo_id NUMBER(18) NOT NULL,
    nome              VARCHAR2(70 CHAR),
    ordem             NUMBER(10),
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE processo_etapa ADD CONSTRAINT processo_etapa_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_processo_etapa
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
