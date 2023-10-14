create user JUNTA_MEDIC
identified by senha
  default tablespace TABLESPACE_NAME
  temporary tablespace TEMP
  profile DEFAULT
  quota unlimited on JUNTA_MEDIC_DADOS;
grant CONNECT to JUNTA_MEDIC;
grant R_SYS_JUNTA_MEDIC to JUNTA_MEDIC;