CREATE TABLE fluxo_processo (
    id                  NUMBER(18) NOT NULL,
    nome_interno        VARCHAR2(70 CHAR),
    nome_apresentacao   VARCHAR2(70 CHAR),
    dm_tipo_processo_id NUMBER(10) NOT NULL,
    ativo               NUMBER(1),
    created_at          DATE,
    created_by          NUMBER(18),
    updated_at          DATE,
    updated_by          NUMBER(18)
);

ALTER TABLE fluxo_processo ADD CONSTRAINT fluxo_processo_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_fluxo_processo
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
