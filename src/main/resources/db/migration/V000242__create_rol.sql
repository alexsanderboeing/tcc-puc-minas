create table rol
(
    id         number(18) not null,
    vigencia   date,
    versao     varchar2(10 char),
    descricao  varchar2(120 char),
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table rol
    add constraint rol_pk primary key (id);

alter table rol
    add constraint rol_uk unique (vigencia);

alter table rol
    add constraint rol_created_by_fk
        foreign key (created_by) references usuario (id);

alter table rol
    add constraint rol_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_rol minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
