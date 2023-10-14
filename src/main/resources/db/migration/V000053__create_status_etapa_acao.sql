CREATE TABLE status_etapa_acao (
    id                      NUMBER(18) NOT NULL,
    acao_id                 NUMBER(18) NOT NULL,
    status_etapa_id         NUMBER(18) NOT NULL,
    status_etapa_destino_id NUMBER(18) NOT NULL,
    created_at              DATE,
    created_by              NUMBER(18),
    updated_at              DATE,
    updated_by              NUMBER(18)
);

ALTER TABLE status_etapa_acao ADD CONSTRAINT status_etapa_acao_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_status_etapa_acao
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
