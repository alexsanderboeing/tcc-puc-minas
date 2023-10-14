CREATE TABLE desempatador_preco (
    id                  NUMBER(18) NOT NULL,
    desempatador_id     NUMBER(18) NOT NULL,
    vigencia            DATE,
    valor               NUMBER(14, 2),
    percentual_reajuste NUMBER(14, 6),
    created_at          DATE,
    created_by          NUMBER(18),
    updated_at          DATE,
    updated_by          NUMBER(18)
);

ALTER TABLE desempatador_preco ADD CONSTRAINT desempatador_preco_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_desempatador_preco
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
