CREATE TABLE profissional_assist_conselho (
    id                         NUMBER(18) NOT NULL,
    profissional_assistente_id NUMBER(18) NOT NULL,
    dm_tipo_conselho_id        NUMBER(10) NOT NULL,
    uf_conselho                VARCHAR2(2 CHAR),
    numero_conselho            VARCHAR2(15 CHAR),
    created_at                 DATE,
    created_by                 NUMBER(18),
    updated_at                 DATE,
    updated_by                 NUMBER(18)
);

CREATE INDEX profissional_conselho_ix ON
    profissional_assist_conselho (
        uf_conselho
    ASC,
        numero_conselho
    ASC );

ALTER TABLE profissional_assist_conselho ADD CONSTRAINT profissional_assist_cons_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_profissional_assist_conselho
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
