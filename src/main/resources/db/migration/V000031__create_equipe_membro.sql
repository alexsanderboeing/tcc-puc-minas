CREATE TABLE equipe_membro (
    id                 NUMBER(18) NOT NULL,
    equipe_trabalho_id NUMBER(18) NOT NULL,
    usuario_ext_id     NUMBER(18),
    created_at         DATE,
    created_by         NUMBER(18),
    updated_at         DATE,
    updated_by         NUMBER(18)
);

ALTER TABLE equipe_membro ADD CONSTRAINT equipe_trabalho_membros_pk PRIMARY KEY (id);

CREATE SEQUENCE s_equipe_membro
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
