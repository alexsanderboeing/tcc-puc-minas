CREATE TABLE calendario (
    id         NUMBER(18) NOT NULL,
    nome       VARCHAR2(70 CHAR),
    ativo      NUMBER(1),
    created_at DATE,
    created_by NUMBER(18),
    updated_at DATE,
    updated_by NUMBER(18)
);

ALTER TABLE calendario ADD CONSTRAINT calendario_pk PRIMARY KEY ( id );


CREATE SEQUENCE s_calendario
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
