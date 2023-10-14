alter table PROCESSO_HIST_NOTIFICACAO add classificacao_anexo_id NUMBER(18);

alter table PROCESSO_HIST_NOTIFICACAO
    add constraint processo_hist_notificacao_classificacao_anexo_fk foreign key (classificacao_anexo_id)
        references classificacao_anexo (id);
