CREATE TABLE beneficiario (
    id                      NUMBER(18) NOT NULL,
    operadora_id            NUMBER(18) NOT NULL,
    pre_cadastro            DATE,
    codigo                  VARCHAR2(20 CHAR),
    nome                    VARCHAR2(120 CHAR),
    dm_sexo_beneficiario_id NUMBER(10) NOT NULL,
    nascimento              DATE,
    observacao              VARCHAR2(500 CHAR),
    created_at              DATE,
    created_by              NUMBER(18),
    updated_at              DATE,
    updated_by              NUMBER(18)
);

CREATE INDEX beneficiario_001_ix ON
    beneficiario (
        codigo
    ASC,
        operadora_id
    ASC );

ALTER TABLE beneficiario ADD CONSTRAINT beneficiario_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_beneficiario
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
