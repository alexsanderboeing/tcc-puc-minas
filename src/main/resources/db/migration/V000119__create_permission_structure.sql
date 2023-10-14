CREATE TABLE perfil (
                         id NUMBER(18) NOT NULL,
                         type varchar2(255) NOT NULL,
                         enabled NUMBER(1) DEFAULT 1,
                         created_at        DATE,
                         created_by        NUMBER(18),
                         updated_at        DATE,
                         updated_by        NUMBER(18)
);

ALTER TABLE perfil
    ADD CONSTRAINT perfil_pk
        PRIMARY KEY (id);

CREATE SEQUENCE s_perfil
    MINVALUE 2 MAXVALUE 999999999999999999
    NOCACHE ORDER;

CREATE TABLE permissao (
                            id NUMBER(18) NOT NULL,
                            permission_parent_id NUMBER(18) NULL,
                            key varchar2(255) NOT NULL,
                            type varchar2(255) NOT NULL,
                            feature_name varchar2(255) NOT NULL,
                            description varchar2(255) NOT NULL,
                            created_at        DATE,
                            created_by        NUMBER(18),
                            updated_at        DATE,
                            updated_by        NUMBER(18)
);

ALTER TABLE permissao
    ADD CONSTRAINT permissao_pk
        PRIMARY KEY (id);

ALTER TABLE permissao
    ADD CONSTRAINT permissao_permission_parent_id_fk
        FOREIGN KEY (permission_parent_id) REFERENCES permissao (id);

CREATE SEQUENCE s_permissao
    MINVALUE 1 MAXVALUE 999999999999999999
    NOCACHE ORDER;

CREATE TABLE perfil_permissao (
                                    permissao_id NUMBER(18) NOT NULL,
                                    perfil_id NUMBER(18) NOT NULL
);

ALTER TABLE perfil_permissao
    ADD CONSTRAINT perfil_permissao_pk
        PRIMARY KEY (permissao_id, perfil_id);

ALTER TABLE perfil_permissao
    ADD CONSTRAINT permissao_perfil_permissao_id_fk
        FOREIGN KEY (permissao_id) REFERENCES permissao (id);

ALTER TABLE perfil_permissao
    ADD CONSTRAINT permissao_perfil_perfil_id_fk
        FOREIGN KEY (perfil_id) REFERENCES perfil (id);

CREATE TABLE usuario_perfil (
                              id NUMBER(18) NOT NULL,
                              usuario_id NUMBER(18) NOT NULL,
                              perfil_id NUMBER(18) NOT NULL,
                              is_default NUMBER(1) DEFAULT 0,
                              created_at        DATE,
                              created_by        NUMBER(18),
                              updated_at        DATE,
                              updated_by        NUMBER(18)
);

ALTER TABLE usuario_perfil
    ADD CONSTRAINT usuario_perfil_pk
        PRIMARY KEY (id);

ALTER TABLE usuario_perfil
    ADD CONSTRAINT usuario_perfil_usario_id_perfil_id_uk
        UNIQUE (usuario_id, perfil_id);

CREATE SEQUENCE s_usuario_perfil
    MINVALUE 2 MAXVALUE 999999999999999999
    NOCACHE ORDER;
