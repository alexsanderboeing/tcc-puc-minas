CREATE TABLE modelo_carta_notificacao (
    id              NUMBER(18) NOT NULL,
    modelo_carta_id NUMBER(18) NOT NULL,
    notificacao_id  NUMBER(18) NOT NULL,
    assunto         VARCHAR2(120 CHAR),
    corpo           VARCHAR2(4000 CHAR),
    papel_id        NUMBER(18),
    celular         VARCHAR2(20 CHAR),
    created_at      DATE,
    created_by      NUMBER(18),
    updated_at      DATE,
    updated_by      NUMBER(18)
);

ALTER TABLE modelo_carta_notificacao ADD CONSTRAINT modelo_carta_notificacao_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_modelo_carta_notificacao
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;

