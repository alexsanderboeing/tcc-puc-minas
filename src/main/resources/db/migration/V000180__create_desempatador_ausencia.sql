create table desempatador_ausencia (
    id number(18) not null,
    desempatador_id number(18) not null,
    data_de date not null,
    data_ate date not null,
    motivo varchar2(120 char) not null,
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table desempatador_ausencia
    add constraint desempatador_ausencia_pk
        primary key (id);

alter table desempatador_ausencia
    add constraint desempatador_ausencia_fk
        foreign key (desempatador_id) references desempatador (id);

create sequence s_desempatador_ausencia
    minvalue 1 maxvalue 999999999999999999
    nocache order;
