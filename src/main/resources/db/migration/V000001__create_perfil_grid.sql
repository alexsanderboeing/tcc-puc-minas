CREATE TABLE perfil_grid (
    id                NUMBER(18) NOT NULL,
    usuario_id        NUMBER(18) NOT NULL,
    nome_grid         VARCHAR2(200) NOT NULL,
    dados_colunas     VARCHAR2(4000 CHAR),
    created_at        DATE,
    created_by        NUMBER(18),
    updated_at        DATE,
    updated_by        NUMBER(18)
);

ALTER TABLE perfil_grid
    ADD CONSTRAINT perfil_grid_pk
        PRIMARY KEY (id);

CREATE SEQUENCE s_perfil_grid
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
