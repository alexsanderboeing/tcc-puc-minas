create table item_tabela
(
    id         number(18) not null,
    codigo     varchar2(2 char),
    descricao  varchar2(130 char),
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table item_tabela
    add constraint tabela_item_pk primary key (id);

alter table item_tabela
    add constraint item_tabela_created_by_fk
        foreign key (created_by) references usuario (id);

alter table item_tabela
    add constraint item_tabela_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_item_tabela minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
