ALTER TABLE MODELO_CARTA_NOTIFICACAO ADD CORPO_TMP CLOB;

UPDATE MODELO_CARTA_NOTIFICACAO SET CORPO_TMP = CORPO;

ALTER TABLE MODELO_CARTA_NOTIFICACAO DROP COLUMN CORPO;

ALTER TABLE MODELO_CARTA_NOTIFICACAO RENAME COLUMN CORPO_TMP TO CORPO;

ALTER TABLE STATUS_ETAPA_ACAO_NOTIF ADD CORPO_TMP CLOB;

UPDATE STATUS_ETAPA_ACAO_NOTIF SET CORPO_TMP = CORPO;

ALTER TABLE STATUS_ETAPA_ACAO_NOTIF DROP COLUMN CORPO;

ALTER TABLE STATUS_ETAPA_ACAO_NOTIF RENAME COLUMN CORPO_TMP TO CORPO;
