ALTER TABLE PROCESSO add JUSTIFICATIVA_OPERADORA_CLOB CLOB;
UPDATE PROCESSO SET JUSTIFICATIVA_OPERADORA_CLOB = JUSTIFICATIVA_OPERADORA;
ALTER TABLE PROCESSO DROP COLUMN JUSTIFICATIVA_OPERADORA;
ALTER TABLE PROCESSO RENAME COLUMN JUSTIFICATIVA_OPERADORA_CLOB to JUSTIFICATIVA_OPERADORA;

ALTER TABLE PROCESSO ADD PARECER_DESEMPATADOR_CLOB CLOB;
UPDATE PROCESSO SET PARECER_DESEMPATADOR_CLOB = PARECER_DESEMPATADOR;
ALTER TABLE PROCESSO DROP COLUMN PARECER_DESEMPATADOR;
ALTER TABLE PROCESSO RENAME COLUMN PARECER_DESEMPATADOR_CLOB to PARECER_DESEMPATADOR;

ALTER TABLE PROCESSO ADD PARECER_DESEMPATADOR_RASC_CLOB CLOB;
UPDATE PROCESSO SET PARECER_DESEMPATADOR_RASC_CLOB = PARECER_DESEMPATADOR_RASCUNHO;
ALTER TABLE PROCESSO DROP COLUMN PARECER_DESEMPATADOR_RASCUNHO;
ALTER TABLE PROCESSO RENAME COLUMN PARECER_DESEMPATADOR_RASC_CLOB to PARECER_DESEMPATADOR_RASCUNHO;