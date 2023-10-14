CREATE TABLE status_etapa (
    id                 NUMBER(18) NOT NULL,
    processo_etapa_id  NUMBER(18) NOT NULL,
    nome               VARCHAR2(70 CHAR),
    disponivel_chat    NUMBER(1),
    restringe_papel_id NUMBER(18) NOT NULL,
    preve_sla          NUMBER(1),
    dm_contagem_sla_id NUMBER(10),
    tempo_horas_sla    NUMBER(4),
    calendario_id      NUMBER(18) NOT NULL,
    created_at         DATE,
    created_by         NUMBER(18),
    updated_at         DATE,
    updated_by         NUMBER(18)
);

ALTER TABLE status_etapa ADD CONSTRAINT status_etapa_pk PRIMARY KEY ( id );


CREATE SEQUENCE s_status_etapa
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
