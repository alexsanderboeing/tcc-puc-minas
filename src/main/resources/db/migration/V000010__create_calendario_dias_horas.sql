CREATE TABLE calendario_dias_horas (
    id               NUMBER(18) NOT NULL,
    calendario_id    NUMBER(18) NOT NULL,
    dm_dia_semana_id NUMBER(10) NOT NULL,
    hora_inicial     VARCHAR2(8 CHAR),
    hora_final       VARCHAR2(8 CHAR),
    created_at       DATE,
    created_by       NUMBER(18),
    updated_at       DATE,
    updated_by       NUMBER(18)
);

ALTER TABLE calendario_dias_horas ADD CONSTRAINT calendario_dias_horas_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_calendario_dias_horas
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
