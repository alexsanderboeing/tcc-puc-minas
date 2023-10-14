CREATE TABLE desempatador_conselho (
    id                  NUMBER(18) NOT NULL,
    dm_tipo_conselho_id NUMBER(10) NOT NULL,
    uf_conselho         VARCHAR2(2 CHAR),
    numero_conselho     VARCHAR2(15 CHAR),
    desempatador_id     NUMBER(18) NOT NULL,
    created_at          DATE,
    created_by          NUMBER(18),
    updated_at          DATE,
    updated_by          NUMBER(18)
);

CREATE INDEX conselho_ix ON
    desempatador_conselho (
        uf_conselho
    ASC,
        numero_conselho
    ASC );

ALTER TABLE desempatador_conselho ADD CONSTRAINT desempatador_conselho_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_desempatador_conselho
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
