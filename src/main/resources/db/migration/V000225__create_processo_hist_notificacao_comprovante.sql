create table PROCESSO_HIST_NOTIFICACAO_COMPROVANTE
(
    id                                  number(18)    not null,
    processo_historico_notificacao_id   number(18)    not null,
    comprovante_base64			        clob,
    nome                                varchar2(120) not null,
    data_recebimento                    date		  not null,
    created_at                          date,
    created_by                          number(18),
    updated_at                          date,
    updated_by                          number(18)
);

alter table PROCESSO_HIST_NOTIFICACAO_COMPROVANTE
    add constraint processo_hist_notificacao_comprovante_pk
        primary key (id);

alter table PROCESSO_HIST_NOTIFICACAO_COMPROVANTE
    add constraint processo_hist_notificacao_comprovante_processo_hist_notificacao_id_fk
        foreign key (processo_historico_notificacao_id) references processo_hist_notificacao (id);

alter table PROCESSO_HIST_NOTIFICACAO_COMPROVANTE
    add constraint processo_hist_notificacao_comprovante_created_by_fk
        foreign key (created_by) references usuario (id);

alter table PROCESSO_HIST_NOTIFICACAO_COMPROVANTE
    add constraint processo_hist_notificacao_comprovante_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_processo_hist_notificacao_comprovante
    minvalue 1 maxvalue 999999999999999999
    nocache order;
