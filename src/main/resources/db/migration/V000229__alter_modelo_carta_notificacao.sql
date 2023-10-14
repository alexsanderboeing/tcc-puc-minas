alter table MODELO_CARTA_NOTIFICACAO add classificacao_anexo_id NUMBER(18);

alter table MODELO_CARTA_NOTIFICACAO
    add constraint modelo_carta_notificacao_classificacao_anexo_fk foreign key (classificacao_anexo_id)
        references classificacao_anexo (id);
