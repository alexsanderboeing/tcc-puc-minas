create table cep
(
    id          number(18) not null,
    codigo         number(8)  not null,
    logradouro  varchar2(120 char),
    complemento varchar2(120 char),
    bairro      varchar2(120 char),
    localidade  varchar2(120 char),
    uf          varchar2(2),
    ddd         varchar2(3),
    ibge        varchar2(18),
    gia         varchar2(120 char),
    siafi       varchar2(120 char),
    created_at  date,
    created_by  number(18),
    updated_at  date,
    updated_by  number(18)
);

alter table cep
    add constraint cep_pk primary key (id);

alter table cep
    add constraint cep_uk unique (codigo);

alter table cep
    add constraint cep_created_by_fk
        foreign key (created_by) references usuario (id);

alter table cep
    add constraint cep_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_cep minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
