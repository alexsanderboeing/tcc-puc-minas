alter table processo drop column cobrar_processo;

alter table processo
    add constraint dm_cobrar_processo_fk foreign key (dm_cobrar_processo_id)
        references dominio (id);
