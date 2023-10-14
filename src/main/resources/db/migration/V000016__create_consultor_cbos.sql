CREATE TABLE consultor_cbos (
    id           NUMBER(18) NOT NULL,
    cbos_ext_id  NUMBER NOT NULL,
    consultor_id NUMBER(18) NOT NULL,
    created_at   DATE,
    created_by   NUMBER(18),
    updated_at   DATE,
    updated_by   NUMBER(18)
);

ALTER TABLE consultor_cbos ADD CONSTRAINT consultor_cbos_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_consultor_cbos
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
