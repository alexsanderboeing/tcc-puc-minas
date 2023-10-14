create table PROCESSO_HIST_NOTIFICACAO_RESPOSTA (
    id                                  number(18)    not null,
    processo_historico_notificacao_id   number(18)    not null,
	message_id_resposta					varchar(100)  not null,
	resposta_recebida					CLOB		  not null,
	data_resposta						date 		  not null,
	data_encaminhamento					date,
    created_at                          date,
    created_by                          number(18),
    updated_at                          date,
    updated_by                          number(18)
);

alter table PROCESSO_HIST_NOTIFICACAO_RESPOSTA
    add constraint processo_hist_notificacao_resposta_pk
        primary key (id);

alter table PROCESSO_HIST_NOTIFICACAO_RESPOSTA
    add constraint processo_hist_notificacao_resposta_created_by_fk
        foreign key (created_by) references usuario (id);

alter table PROCESSO_HIST_NOTIFICACAO_RESPOSTA
    add constraint processo_hist_notificacao_resposta_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_processo_hist_notificacao_resposta
    minvalue 1 maxvalue 999999999999999999
    nocache order;
