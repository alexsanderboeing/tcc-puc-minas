CREATE TABLE notificacao (
    id         NUMBER(18) NOT NULL,
    forma      VARCHAR2(1 CHAR),
    created_at DATE,
    created_by NUMBER(18),
    updated_at DATE,
    updated_by NUMBER(18)
);

ALTER TABLE notificacao ADD CONSTRAINT notificacao_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_notificacao
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
