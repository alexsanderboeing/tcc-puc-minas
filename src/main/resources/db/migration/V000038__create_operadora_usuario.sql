CREATE TABLE operadora_usuario (
    usuario_ext_id NUMBER(18) NOT NULL,
    operadora_id   NUMBER(18) NOT NULL,
    created_at     DATE,
    created_by     NUMBER(18),
    updated_at     DATE,
    updated_by     NUMBER(18)
);

ALTER TABLE operadora_usuario ADD CONSTRAINT operadora_usuario_pk PRIMARY KEY ( usuario_ext_id, operadora_id );

CREATE SEQUENCE s_operadora_usuario
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
