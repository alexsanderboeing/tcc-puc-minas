alter table chat_mensagem add papel_destino_id NUMBER(18) default null NOT NULL;

alter table chat_mensagem
    add constraint chat_papel_destino_fk foreign key (papel_destino_id)
        references papel (id);
