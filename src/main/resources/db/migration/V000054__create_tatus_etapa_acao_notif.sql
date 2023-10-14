CREATE TABLE status_etapa_acao_notif (
    id                   NUMBER(18) NOT NULL,
    notificacao_id       NUMBER(18) NOT NULL,
    status_etapa_acao_id NUMBER(18) NOT NULL,
    assunto              VARCHAR2(120 CHAR),
    papel_id             NUMBER(18) NOT NULL,
    corpo                VARCHAR2(4000 CHAR),
    anexar_carta         NUMBER(1),
    created_at           DATE,
    created_by           NUMBER(18),
    updated_at           DATE,
    updated_by           NUMBER(18)
);

ALTER TABLE status_etapa_acao_notif ADD CONSTRAINT status_etapa_acao_notif_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_status_etapa_acao_notif
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
