CREATE TABLE operadora (
    id                            NUMBER(18) NOT NULL,
    cnpj                          NUMBER(14),
    nome_fantasia                 VARCHAR2(120 CHAR),
    razao_social                  VARCHAR2(120 CHAR),
    registro_ans                  VARCHAR2(20 CHAR),
    criacao                       DATE,
    logo_base64                   CLOB,
    equipe_administrativo_fesc_id NUMBER(18),
    equipe_auditor_qualidade_id   NUMBER(18),
    created_at                    DATE,
    created_by                    NUMBER(18),
    updated_at                    DATE,
    updated_by                    NUMBER(18)
);

ALTER TABLE operadora ADD CONSTRAINT operadora_pk PRIMARY KEY ( id );

ALTER TABLE operadora ADD CONSTRAINT operadora_uk UNIQUE ( cnpj );

CREATE SEQUENCE s_operadora
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
