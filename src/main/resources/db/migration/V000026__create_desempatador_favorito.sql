CREATE TABLE desempatador_favorito (
    id              NUMBER(18) NOT NULL,
    operadora_id    NUMBER(18) NOT NULL,
    desempatador_id NUMBER(18) NOT NULL,
    created_at      DATE,
    created_by      NUMBER(18),
    updated_at      DATE,
    updated_by      NUMBER(18)
);

ALTER TABLE desempatador_favorito ADD CONSTRAINT desempatador_favorito_pk PRIMARY KEY ( id );

ALTER TABLE desempatador_favorito ADD CONSTRAINT desempatador_favorito_uk UNIQUE (operadora_id, desempatador_id );

CREATE SEQUENCE s_desempatador_favorito
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
