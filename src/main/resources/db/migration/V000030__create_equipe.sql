CREATE TABLE equipe (
    id         NUMBER(18) NOT NULL,
    nome       VARCHAR2(70 CHAR),
    created_at DATE,
    created_by NUMBER(18),
    updated_at DATE,
    updated_by NUMBER(18)
);

ALTER TABLE equipe ADD CONSTRAINT equipe_trabalho_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_equipe
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
