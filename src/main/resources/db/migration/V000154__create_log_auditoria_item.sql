create table LOG_AUDITORIA_ITEM
(
  id                        NUMBER(18) not null,
  log_auditoria_id			NUMBER(18) not null,
  campo	                    VARCHAR2(100) not null,
  valor_antigo              CLOB,
  valor_atual               CLOB,
  created_at                DATE,
  created_by                NUMBER(18),
  updated_at                DATE,
  updated_by                NUMBER(18)
);

alter table LOG_AUDITORIA_ITEM
  add constraint LOG_AUDITORIA_ITEM_PK
    primary key (ID);

alter table LOG_AUDITORIA_ITEM
    add constraint LOG_AUDITORIA_ID_FK
        foreign key (LOG_AUDITORIA_ID) references LOG_AUDITORIA (ID);

create sequence S_LOG_AUDITORIA_ITEM minvalue 1 maxvalue 999999999999999999
    start with 1 increment by 1 nocache order;
