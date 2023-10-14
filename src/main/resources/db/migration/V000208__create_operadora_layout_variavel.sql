create table operadora_layout_variavel
(
    id                  number(18)   not null,
    operadora_layout_id number(18)   not null,
    variavel_id         number(18)   not null,
    conteudo            varchar2(200) not null,
    created_at          date,
    created_by          number(18),
    updated_at          date,
    updated_by          number(18)
);

alter table operadora_layout_variavel
    add constraint operadora_layout_variavel_pk
        primary key (id);

alter table operadora_layout_variavel
    add constraint operadora_layout_variavel_operadora_layout_id_fk
        foreign key (operadora_layout_id) references operadora_layout (id);

alter table operadora_layout_variavel
    add constraint operadora_layout_variavel_variavel_id_fk
        foreign key (variavel_id) references variavel (id);

alter table operadora_layout_variavel
    add constraint operadora_layout_variavel_created_by_fk
        foreign key (created_by) references usuario (id);

alter table operadora_layout_variavel
    add constraint operadora_layout_variavel_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_operadora_layout_variavel
    minvalue 1 maxvalue 999999999999999999
    nocache order;
