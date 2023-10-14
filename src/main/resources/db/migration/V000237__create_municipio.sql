create table municipio
(
    id          number(18) not null,
    nome        varchar2(120 char),
    codigo_ibge number(18),
    estado_id   number(18) not null,
    created_at  date,
    created_by  number(18),
    updated_at  date,
    updated_by  number(18)
);

alter table municipio
    add constraint municipio_pk primary key (id);

alter table municipio
    add constraint municipio_estado_fk foreign key (estado_id) references estado (id);

alter table municipio
    add constraint municipio_created_by_fk
        foreign key (created_by) references usuario (id);

alter table municipio
    add constraint municipio_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_municipio minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
