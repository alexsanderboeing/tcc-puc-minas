CREATE TABLE papel (
    id         NUMBER(18) NOT NULL,
    perfil_id  NUMBER(18),
    nome       VARCHAR2(70 CHAR),
    created_at DATE,
    created_by NUMBER(18),
    updated_at DATE,
    updated_by NUMBER(18)
);

ALTER TABLE papel ADD CONSTRAINT papel_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_papel
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
