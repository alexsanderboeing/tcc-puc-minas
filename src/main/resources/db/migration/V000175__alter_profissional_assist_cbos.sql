ALTER TABLE PROFISSIONAL_ASSIS_CBOS ADD MIGRACAO_CBOS_REALIZADA NUMBER(1);

UPDATE PROFISSIONAL_ASSIS_CBOS SET MIGRACAO_CBOS_REALIZADA = 0;
