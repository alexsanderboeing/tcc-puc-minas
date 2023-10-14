alter table PROCESSO_ANEXO add modelo_carta_id NUMBER(18);
alter table PROCESSO_ANEXO add constraint PROCESSO_ANEXO_MODELO_CARTA_FK foreign key (MODELO_CARTA_ID)  references modelo_carta (ID);
