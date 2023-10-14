CREATE TABLE processo_hist_notificacao (
    id                   NUMBER(18) NOT NULL,
    processo_id          NUMBER(18) NOT NULL,
    processo_etapa_id    NUMBER(18) NOT NULL,
    notificacao_id       NUMBER(18) NOT NULL,
    data                 DATE,
    anexo_id             NUMBER(18) NOT NULL,
    assunto              VARCHAR2(120 CHAR),
    corpo                VARCHAR2(4000 CHAR),
    papel_id             NUMBER(18) NOT NULL,
    comprovante_base64   CLOB,
    nome_destinatario    VARCHAR2(120 CHAR),
    email_destinatario   VARCHAR2(120 CHAR),
    celular_destinatario VARCHAR2(20 CHAR),
    chat_mensagem_id     NUMBER(18) NOT NULL,
    created_at           DATE,
    created_by           NUMBER(18),
    updated_at           DATE,
    updated_by           NUMBER(18)
);

ALTER TABLE processo_hist_notificacao ADD CONSTRAINT processo_historico_notif_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_processo_hist_notificacao
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
