CREATE TABLE processo_item (
    id                            NUMBER(18) NOT NULL,
    processo_id                   NUMBER(18) NOT NULL,
    item                          NUMBER(18),
    descricao_item                VARCHAR2(150 CHAR),
    valor_unitario                NUMBER(14, 4),
    quantidade                    NUMBER(13, 5),
    justificativa_desempatador    VARCHAR2(500 CHAR),
    dm_tipo_item_processo_id      NUMBER(10) NOT NULL,
    dm_parecer_operadora_id       NUMBER(10),
    dm_parecer_desempatador_id    NUMBER(10),
    dm_parecer_desemp_rascunho_id NUMBER(10),
    justificativa_desemp_rascunho VARCHAR2(500 CHAR),
    created_at                    DATE,
    created_by                    NUMBER(18),
    updated_at                    DATE,
    updated_by                    NUMBER(18)
);

ALTER TABLE processo_item ADD CONSTRAINT processo_item_pk PRIMARY KEY ( id );

CREATE SEQUENCE s_processo_item
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
