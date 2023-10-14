CREATE TABLE auditor_conselho (
    id               NUMBER(18) NOT NULL,
    auditor_id       NUMBER(18) NOT NULL,
    tipo_conselho_id NUMBER(10) NOT NULL,
    uf_conselho      VARCHAR2(2 CHAR),
    numero_conselho  VARCHAR2(15 CHAR),
    created_at       DATE,
    created_by       NUMBER(18),
    updated_at       DATE,
    updated_by       NUMBER(18)
);

CREATE INDEX auditor_conselho_ix ON
    auditor_conselho (
        uf_conselho
    ASC,
        numero_conselho
    ASC );

ALTER TABLE auditor_conselho ADD CONSTRAINT auditor_conselho_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_auditor_conselho
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
