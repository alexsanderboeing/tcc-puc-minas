create table item
(
    id             number(18) not null,
    item_tipo_id   number(18) not null,
    item_tabela_id number(18) not null,
    codigo         varchar2(18 char),
    descricao      varchar2(255 char),
    inicio         date,
    final          date,
    created_at     date,
    created_by     number(18),
    updated_at     date,
    updated_by     number(18)
);

alter table item
    add constraint item_pk primary key (id);

alter table item
    add constraint item_item_tipo_fk foreign key (item_tipo_id)
        references item_tipo (id);

alter table item
    add constraint item_tabela_item_fk foreign key (item_tabela_id)
        references item_tabela (id);

create sequence s_item minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
