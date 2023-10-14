create table estado
(
    id          number(18) not null,
    nome        varchar2(120 char),
    sigla       varchar2(2 char),
    codigo_ibge number(18),
    created_at  date,
    created_by  number(18),
    updated_at  date,
    updated_by  number(18)
);

alter table estado
    add constraint estado_pk primary key (id);

alter table estado
    add constraint estado_created_by_fk
        foreign key (created_by) references usuario (id);

alter table estado
    add constraint estado_updated_by_fk
        foreign key (updated_by) references usuario (id);


create sequence s_estado minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
