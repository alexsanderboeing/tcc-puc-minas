create table operadora_layout
(
    id                            number(18)    not null,
    operadora_id                  number(18)    not null,
    nome                          varchar2(120) not null,
    ativo                         number(1)     not null,
    modelo_carta_cabecalho_base64 clob,
    modelo_carta_rodape_base64    clob,
    created_at                    date,
    created_by                    number(18),
    updated_at                    date,
    updated_by                    number(18)
);

alter table operadora_layout
    add constraint operadora_layout_pk
        primary key (id);

alter table operadora_layout
    add constraint operadora_layout_operadora_id_fk
        foreign key (operadora_id) references operadora (id);

alter table operadora_layout
    add constraint operadora_layout_created_by_fk
        foreign key (created_by) references usuario (id);

alter table operadora_layout
    add constraint operadora_layout_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_operadora_layout
    minvalue 1 maxvalue 999999999999999999
    nocache order;
