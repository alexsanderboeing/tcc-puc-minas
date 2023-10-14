CREATE TABLE auditor_cbos (
    id          NUMBER(18) NOT NULL,
    auditor_id  NUMBER(18) NOT NULL,
    cbos_ext_id NUMBER NOT NULL,
    created_at  DATE,
    created_by  NUMBER(18),
    updated_at  DATE,
    updated_by  NUMBER(18)
);

ALTER TABLE auditor_cbos ADD CONSTRAINT auditor_cbos_pk PRIMARY KEY ( id );

ALTER TABLE auditor_cbos ADD CONSTRAINT auditor_cbos_uk UNIQUE ( auditor_id,
                                                                 cbos_ext_id );


CREATE SEQUENCE s_auditor_cbos
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
