ALTER TABLE USUARIO_RECOVERY ADD DATA_EXPIRACAO DATE;

UPDATE USUARIO_RECOVERY SET DATA_EXPIRACAO = SYSDATE;

ALTER TABLE USUARIO_RECOVERY MODIFY DATA_EXPIRACAO NOT NULL;
