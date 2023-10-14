create table OPERADORA_INTEGRACAO (
  id                               NUMBER(18) not null,
  operadora_id                     NUMBER(18) not null,
  dm_integracao_operadora_id       NUMBER(18) default 70 not null,
  dm_forma_autentica_integracao_id NUMBER(18) default 71 not null,
  clientid                         VARCHAR2(250),
  clientsecret                     VARCHAR2(250),
  ativo                            NUMBER(1) default 0 not null,
  url                              VARCHAR2(600) not null,
  created_at                       DATE,
  created_by                       NUMBER(18),
  updated_at                       DATE,
  updated_by                       NUMBER(18)
);

alter table OPERADORA_INTEGRACAO
    add constraint OPERADORA_INTEGRACAO_PK
        primary key (ID);

alter table OPERADORA_INTEGRACAO
    add constraint DM_FORMA_AUTENTICA_INTEGRACAO_ID_FK
        foreign key (DM_FORMA_AUTENTICA_INTEGRACAO_ID) references DOMINIO (ID);

alter table OPERADORA_INTEGRACAO
    add constraint DM_INTEGRACAO_OPERADORA_ID_FK
        foreign key (DM_INTEGRACAO_OPERADORA_ID) references DOMINIO (ID);

alter table OPERADORA_INTEGRACAO
    add constraint OPERADORA_INTEGRACAO_OPERADORA_FK
        foreign key (OPERADORA_ID) references OPERADORA (ID);

alter table OPERADORA_INTEGRACAO
    add constraint OPERADORA_INTEGRACAO_UK
        unique (OPERADORA_ID, DM_INTEGRACAO_OPERADORA_ID);

create sequence S_OPERADORA_INTEGRACAO minvalue 1 maxvalue 999999999999999999
    start with 1 increment by 1 nocache order;

insert into dominio
    values (70, 'dm_integracao_operadora', '1', 'busca_dados_autoriz_novo_processo', null,null,null,null);

insert into dominio
    values (71, 'dm_forma_autentica_integracao', '1', 'oauth2', null,null,null,null);