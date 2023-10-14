CREATE TABLE ESPECIALIDADE (
	ID NUMBER(18) NOT NULL,
	NOME VARCHAR2(120),
	ATIVO NUMBER(1),
	DM_TIPO_CONSELHO_ID NUMBER(10),
	CREATED_AT  date,
    CREATED_BY  number(18),
    UPDATED_AT  date,
    UPDATED_BY  number(18)
);

alter table ESPECIALIDADE
    add constraint ESPECIALIDADE_PK
        primary key (ID);

alter table ESPECIALIDADE
    add constraint ESPECIALIDADE_DOMINIO_FK
        foreign key (DM_TIPO_CONSELHO_ID) references DOMINIO (ID);

create sequence S_ESPECIALIDADE minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 nocache order;
