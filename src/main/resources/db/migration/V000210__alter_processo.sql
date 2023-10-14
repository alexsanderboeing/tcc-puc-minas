alter table processo add operadora_layout_id number(18);

alter table processo
    add constraint processo_operadora_layout_id_fk
        foreign key (operadora_layout_id) references operadora_layout (id);
