alter table chat_mensagem drop constraint chat_mensagem_anexo_fk;
alter table chat_mensagem modify anexo_id null;
alter table chat_mensagem
    add constraint chat_mensagem_anexo_fk foreign key (anexo_id)
        references anexo (id);
alter table chat_mensagem drop column status_etapa_id;
alter table chat_mensagem drop column processo_etapa_id;
