CREATE TABLE equipe_membro_inatividade (
    id               NUMBER(18) NOT NULL,
    equipe_membro_id NUMBER(18) NOT NULL,
    vigencia         DATE,
    final            DATE,
    motivo           VARCHAR2(120 CHAR),
    created_at       DATE,
    created_by       NUMBER(18),
    updated_at       DATE,
    updated_by       NUMBER(18)
);

ALTER TABLE equipe_membro_inatividade ADD CONSTRAINT equipe_membros_inatividade_pk PRIMARY KEY (id);

CREATE SEQUENCE s_equipe_membro_inatividade
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
