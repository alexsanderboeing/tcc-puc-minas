CREATE TABLE processo_desempatadores (
    id              NUMBER(18) NOT NULL,
    processo_id     NUMBER(18) NOT NULL,
    desempatador_id NUMBER(18) NOT NULL,
    created_at      DATE,
    created_by      NUMBER(18),
    updated_at      DATE,
    updated_by      NUMBER(18)
);

ALTER TABLE processo_desempatadores ADD CONSTRAINT processo_desempatadores_pk PRIMARY KEY ( id );

ALTER TABLE processo_desempatadores ADD CONSTRAINT processo_desempatadores_uk UNIQUE ( processo_id, desempatador_id );

CREATE SEQUENCE s_processo_desempatadores
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
