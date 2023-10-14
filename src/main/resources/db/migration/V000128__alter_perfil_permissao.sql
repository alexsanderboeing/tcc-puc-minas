delete from perfil_permissao;

ALTER TABLE perfil_permissao ADD id NUMBER(18) NOT NULL;
ALTER TABLE perfil_permissao ADD created_at DATE;
ALTER TABLE perfil_permissao ADD created_by NUMBER(18);
ALTER TABLE perfil_permissao ADD updated_at DATE;
ALTER TABLE perfil_permissao ADD updated_by NUMBER(18);
ALTER TABLE perfil_permissao DROP CONSTRAINT perfil_permissao_pk;

CREATE SEQUENCE s_perfil_permissao
    MINVALUE 43 MAXVALUE 999999999999999999
    NOCACHE ORDER;

ALTER TABLE perfil_permissao
    ADD CONSTRAINT perfil_permissao_pk
        PRIMARY KEY (id);
