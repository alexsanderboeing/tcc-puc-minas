create table papel_perfil (
    id number(18),
    papel_id number(18),
    perfil_id number(18),
    created_at  date,
    created_by  number(18),
    updated_at  date,
    updated_by  number(18)
);

alter table PAPEL_PERFIL add constraint papel_perfil_pk primary key (ID);
alter table PAPEL_PERFIL add constraint papel_perfil_papel_fk foreign key (PAPEL_ID) references papel (ID);

create sequence s_papel_perfil minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
