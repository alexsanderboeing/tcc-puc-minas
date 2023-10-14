create table desempatador_restricao (
    id number(18) not null,
    desempatador_id number(18) not null,
    informacao varchar2(120 char) not null,
    operadora_solicitante_id number(18),
    auditor_id number(18),
    profissional_assistente_id number(18),
    motivo varchar2(120 char) not null,
    created_at date,
    created_by number(18),
    updated_at date,
    updated_by number(18)
);

alter table desempatador_restricao
    add constraint desempatador_restricao_pk
        primary key (id);

alter table desempatador_restricao
    add constraint desempatador_restricao_desempatador_id_fk
        foreign key (desempatador_id) references desempatador (id);

alter table desempatador_restricao
    add constraint desempatador_restricao_operadora_id_fk
        foreign key (operadora_solicitante_id) references operadora (id);

alter table desempatador_restricao
    add constraint desempatador_restricao_auditor_id_fk
        foreign key (auditor_id) references auditor (id);

alter table desempatador_restricao
    add constraint desempatador_restricao_profissional_assistente_id_fk
        foreign key (profissional_assistente_id) references profissional_assistente (id);

create sequence s_desempatador_restricao
    minvalue 1 maxvalue 999999999999999999
    nocache order;
