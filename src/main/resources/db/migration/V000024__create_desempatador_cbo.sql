CREATE TABLE desempatador_cbo (
    id              NUMBER(18) NOT NULL,
    desempatador_id NUMBER(18) NOT NULL,
    cbos_ext_id     NUMBER NOT NULL,
    created_at      DATE,
    created_by      NUMBER(18),
    updated_at      DATE,
    updated_by      NUMBER(18)
);

ALTER TABLE desempatador_cbo ADD CONSTRAINT desempatador_especialidade_pk PRIMARY KEY ( id );

ALTER TABLE desempatador_cbo ADD CONSTRAINT desempatador_cbo_espec_uk UNIQUE (desempatador_id, cbos_ext_id );

CREATE SEQUENCE s_desempatador_cbo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
