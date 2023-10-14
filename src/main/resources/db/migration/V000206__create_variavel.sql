create table variavel
(
    id         number(18)    not null,
    chave      varchar2(150) not null,
    nome       varchar2(170) not null,
    descricao  varchar2(300) not null,
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table variavel
    add constraint variavel_pk
        primary key (id);

alter table variavel
    add constraint variavel_created_by_fk
        foreign key (created_by) references usuario (id);

alter table variavel
    add constraint variavel_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_variavel
    minvalue 1 maxvalue 999999999999999999
    nocache order;
