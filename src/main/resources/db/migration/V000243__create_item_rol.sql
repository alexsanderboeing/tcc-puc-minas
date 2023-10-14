create table item_rol
(
    id         number(18) not null,
    rol_id     number(18) not null,
    item_id    number(18) not null,
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table item_rol
    add constraint item_rol_pk primary key (id);

alter table item_rol
    add constraint item_rol_uk unique (rol_id, item_id);

alter table item_rol
    add constraint item_rol_item_id_item_fk
        foreign key (item_id) references item (id);

alter table item_rol
    add constraint item_rol_rol_id_rol_fk
        foreign key (rol_id) references rol (id);

alter table item_rol
    add constraint item_rol_created_by_fk
        foreign key (created_by) references usuario (id);

alter table item_rol
    add constraint item_rol_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_item_rol minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
