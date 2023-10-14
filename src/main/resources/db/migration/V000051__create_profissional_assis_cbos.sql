CREATE TABLE profissional_assis_cbos (
    id                         NUMBER(18) NOT NULL,
    profissional_assistente_id NUMBER(18) NOT NULL,
    cbos_ext_id                NUMBER NOT NULL,
    created_at                 DATE,
    created_by                 NUMBER(18),
    updated_at                 DATE,
    updated_by                 NUMBER(18)
);

ALTER TABLE profissional_assis_cbos ADD CONSTRAINT profissional_cbos_pk PRIMARY KEY ( id );

ALTER TABLE profissional_assis_cbos ADD CONSTRAINT profissional_assis_cbos_uk UNIQUE ( profissional_assistente_id,
                                                                                       cbos_ext_id );

CREATE SEQUENCE s_profissional_assis_cbos
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;

