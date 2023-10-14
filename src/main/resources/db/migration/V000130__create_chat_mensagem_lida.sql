CREATE TABLE chat_mensagem_lida (
    id                 NUMBER(18) NOT NULL,
    chat_mensagem_id   NUMBER(18) NOT NULL,
    usuario_ext_id     NUMBER(18),
    created_at         DATE,
    created_by         NUMBER(18),
    updated_at         DATE,
    updated_by         NUMBER(18)
);

ALTER TABLE chat_mensagem_lida ADD CONSTRAINT chat_mensagem_lida_pk PRIMARY KEY (id);

CREATE SEQUENCE s_chat_mensagem_lida
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
