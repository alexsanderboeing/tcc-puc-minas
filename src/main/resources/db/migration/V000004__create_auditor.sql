CREATE TABLE auditor (
    id           NUMBER(18) NOT NULL,
    operadora_id NUMBER(18) NOT NULL,
    nome         VARCHAR2(120 CHAR),
    observacao   VARCHAR2(500 CHAR),
    created_at   DATE,
    created_by   NUMBER(18),
    updated_at   DATE,
    updated_by   NUMBER(18)
);

ALTER TABLE auditor ADD CONSTRAINT auditor_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_auditor
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
