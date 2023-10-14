create table LOG_AUDITORIA
(
  id                        NUMBER(18) not null,
  tabela                    VARCHAR2(100) not null,
  tabela_id        			NUMBER(18) not null,
  operacao                  VARCHAR2(10) not null,
  filtro					VARCHAR2(500),
  created_at                DATE,
  created_by                NUMBER(18),
  updated_at                DATE,
  updated_by                NUMBER(18)
);

alter table LOG_AUDITORIA
  add constraint LOG_AUDITORIA_PK
    primary key (ID);

create sequence S_LOG_AUDITORIA minvalue 1 maxvalue 999999999999999999
    start with 1 increment by 1 nocache order;
