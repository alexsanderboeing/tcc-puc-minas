create table item_tipo
(
    id         number(18) not null,
    codigo     number(10),
    descricao  varchar2(120 char),
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table item_tipo
    add constraint item_tipo_pk primary key (id);

alter table item_tipo
    add constraint item_tipo_uk unique (codigo);

alter table item_tipo
    add constraint item_tipo_created_by_fk
        foreign key (created_by) references usuario (id);

alter table item_tipo
    add constraint item_tipo_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_item_tipo minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
