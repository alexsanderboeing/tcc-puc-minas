ALTER TABLE alerta ADD lembrete_id number(18);

alter table alerta
    add constraint ALERTA_LEMBRETE_FK
        foreign key (lembrete_id) references LEMBRETE (ID);
