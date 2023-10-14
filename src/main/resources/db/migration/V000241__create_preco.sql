create table preco
(
    id         number(18) not null,
    nome       varchar2(130 char),
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table preco
    add constraint preco_pk primary key (id);

alter table preco
    add constraint preco_created_by_fk
        foreign key (created_by) references usuario (id);

alter table preco
    add constraint preco_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_preco minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
