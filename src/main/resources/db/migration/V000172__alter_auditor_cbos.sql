ALTER TABLE AUDITOR_CBOS ADD MIGRACAO_CBOS_REALIZADA NUMBER(1);

UPDATE AUDITOR_CBOS SET MIGRACAO_CBOS_REALIZADA = 0;
