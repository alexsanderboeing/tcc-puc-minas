CREATE TABLE status_etapa_acao_perfil (
    id                   NUMBER(18) NOT NULL,
    perfil_id            NUMBER(18) NOT NULL,
    status_etapa_acao_id NUMBER(18) NOT NULL,
    created_at           DATE,
    created_by           NUMBER(18),
    updated_at           DATE,
    updated_by           NUMBER(18)
);

COMMENT ON COLUMN status_etapa_acao_perfil.perfil_id IS
    'Perfis que acessam a ação ';

ALTER TABLE status_etapa_acao_perfil ADD CONSTRAINT status_etapa_acao_perfil_pk PRIMARY KEY ( id );

ALTER TABLE status_etapa_acao_perfil ADD CONSTRAINT status_etapa_acao_perfil_uk UNIQUE ( perfil_id,
                                                                                         status_etapa_acao_id );

CREATE SEQUENCE s_status_etapa_acao_perfil
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
