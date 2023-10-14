CREATE TABLE ALERTA(
	ID NUMBER(18) NOT NULL,
	PROCESSO_ID NUMBER(18) NOT NULL,
	OPERADORA_SOLICITANTE_ID NUMBER(18) NOT NULL,
	PAPEL_USUARIO_ID number(18),
	USUARIO_ID NUMBER(18),
	PAPEL_CHAT_ID number(18),
	ORIGEM VARCHAR2(30),
	LIDO NUMBER(1,0),
	CREATED_AT  date,
    CREATED_BY  number(18),
    UPDATED_AT  date,
    UPDATED_BY  number(18)
);

alter table ALERTA
    add constraint ALERTA_PK
        primary key (ID);
alter table ALERTA
    add constraint ALERTA_PROCESSO_FK
        foreign key (PROCESSO_ID) references processo (ID);
alter table ALERTA
    add constraint ALERTA_OPERADORA_SOLICITANTE_FK
        foreign key (OPERADORA_SOLICITANTE_ID) references OPERADORA (ID);
alter table ALERTA
    add constraint ALERTA_PAPEL_USUARIO_FK
        foreign key (PAPEL_USUARIO_ID) references papel (ID);
alter table ALERTA
    add constraint ALERTA_USUARIO_FK
        foreign key (USUARIO_ID) references usuario (ID);
alter table ALERTA
    add constraint ALERTA_PAPEL_CHAT_FK
        foreign key (PAPEL_CHAT_ID) references papel (ID);

create SEQUENCE S_ALERTA MINVALUE 1 MAXVALUE 999999999999999999 START WITH 1 INCREMENT BY 1 NOCACHE ORDER;
