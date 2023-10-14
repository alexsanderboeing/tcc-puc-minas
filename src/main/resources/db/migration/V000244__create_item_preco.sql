create table item_preco
(
    id          number(18) not null,
    preco_id    number(18) not null,
    item_id     number(18) not null,
    inicio      date,
    final       date,
    valor_ho    number(16, 4),
    valor_co    number(16, 4),
    valor_filme number(16, 4),
    created_at  date,
    created_by  number(18),
    updated_at  date,
    updated_by  number(18)
);

alter table item_preco
    add constraint item_preco_pk primary key (id);

alter table item_preco
    add constraint item_preco_uk unique (preco_id, item_id, inicio);

alter table item_preco
    add constraint item_preco_item_id_item_fk
        foreign key (item_id) references item (id);

alter table item_preco
    add constraint item_preco_preco_id_preco_fk
        foreign key (preco_id) references preco (id);

alter table item_preco
    add constraint item_preco_created_by_fk
        foreign key (created_by) references usuario (id);

alter table item_preco
    add constraint item_preco_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_item_preco minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
