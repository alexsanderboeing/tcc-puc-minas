CREATE TABLE modelo_carta (
    id                NUMBER(18) NOT NULL,
    processo_etapa_id NUMBER(18) NOT NULL,
    nome              VARCHAR2(70 CHAR),
    ativo             NUMBER(1),
    cabecalho         CLOB,
    corpo             CLOB,
    rodape            CLOB,
    anexo_id          NUMBER(18) NOT NULL,
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE modelo_carta ADD CONSTRAINT modelo_carta_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_modelo_carta
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
