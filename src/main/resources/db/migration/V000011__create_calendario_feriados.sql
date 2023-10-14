CREATE TABLE calendario_feriados (
    id                NUMBER(18) NOT NULL,
    calendario_id     NUMBER(18) NOT NULL,
    data              DATE,
    motivo            VARCHAR2(70 CHAR),
    repete_anualmente NUMBER(1),
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE calendario_feriados ADD CONSTRAINT calendario_feriados_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_calendario_feriados
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
