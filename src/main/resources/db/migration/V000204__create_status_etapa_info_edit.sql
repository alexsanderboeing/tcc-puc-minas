CREATE TABLE status_etapa_info_edit (
    id                   NUMBER(18) NOT NULL,
    perfil_id            NUMBER(18) NOT NULL,
    status_etapa_id      NUMBER(18) NOT NULL,
    info_edit_id         NUMBER(18) NOT NULL,
    created_at           DATE,
    created_by           NUMBER(18),
    updated_at           DATE,
    updated_by           NUMBER(18)
);

ALTER TABLE status_etapa_info_edit
    ADD CONSTRAINT status_etapa_info_edit_pk
        PRIMARY KEY (id);

ALTER TABLE status_etapa_info_edit
    ADD CONSTRAINT status_etapa_info_edit_perfil_fk
        FOREIGN KEY (perfil_id) REFERENCES perfil (id);

ALTER TABLE status_etapa_info_edit
    ADD CONSTRAINT status_etapa_info_edit_status_etapa_fk
        FOREIGN KEY (status_etapa_id) REFERENCES status_etapa (id);

ALTER TABLE status_etapa_info_edit
    ADD CONSTRAINT status_etapa_info_edit_fk
        FOREIGN KEY (info_edit_id) REFERENCES info_edit (id);

CREATE SEQUENCE s_status_etapa_info_edit
    MINVALUE 1 MAXVALUE 999999999999999999
        NOCACHE ORDER;
