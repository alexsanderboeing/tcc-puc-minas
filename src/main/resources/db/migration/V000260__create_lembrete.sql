CREATE TABLE LEMBRETE(
	ID NUMBER(18) NOT NULL,
	PROCESSO_ID NUMBER(18) NOT NULL,
	OPERADORA_SOLICITANTE_ID NUMBER(18) NOT NULL,
	PAPEL_DESTINATARIO_ID number(18),
	USUARIO_ID NUMBER(18),
	QUANDO  date,
	QUANDO_NOTIFICAR  date,
	TEXTO VARCHAR2(255),
	TIPO VARCHAR2(30),
	CREATED_AT  date,
    CREATED_BY  number(18),
    UPDATED_AT  date,
    UPDATED_BY  number(18)
);

alter table LEMBRETE
    add constraint LEMBRETE_PK
        primary key (ID);
alter table LEMBRETE
    add constraint LEMBRETE_PROCESSO_FK
        foreign key (PROCESSO_ID) references processo (ID);
alter table LEMBRETE
    add constraint LEMBRETE_OPERADORA_SOLICITANTE_FK
        foreign key (OPERADORA_SOLICITANTE_ID) references OPERADORA (ID);
alter table LEMBRETE
    add constraint LEMBRETE_PAPEL_DESTINATARIO_FK
        foreign key (PAPEL_DESTINATARIO_ID) references papel (ID);
alter table LEMBRETE
    add constraint LEMBRETE_USUARIO_FK
        foreign key (USUARIO_ID) references usuario (ID);
       
create SEQUENCE S_LEMBRETE MINVALUE 1 MAXVALUE 999999999999999999 START WITH 1 INCREMENT BY 1 NOCACHE ORDER;
