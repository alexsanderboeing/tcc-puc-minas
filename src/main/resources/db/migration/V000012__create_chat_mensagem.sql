CREATE TABLE chat_mensagem (
    id                NUMBER(18) NOT NULL,
    processo_id       NUMBER(18) NOT NULL,
    mensagem          VARCHAR2(4000 CHAR),
    data              DATE,
    anexo_id          NUMBER(18) NOT NULL,
    papel_id          NUMBER(18) NOT NULL,
    privado           NUMBER(1),
    status_etapa_id   NUMBER(18) NOT NULL,
    processo_etapa_id NUMBER(18) NOT NULL,
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE chat_mensagem ADD CONSTRAINT chat_mensagem_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_chat_mensagem
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
