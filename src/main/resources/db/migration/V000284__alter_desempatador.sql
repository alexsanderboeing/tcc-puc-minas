ALTER TABLE DESEMPATADOR ADD TEXTO_CLOB CLOB;
UPDATE DESEMPATADOR SET TEXTO_CLOB = MINI_CURRICULO;
ALTER TABLE DESEMPATADOR DROP COLUMN MINI_CURRICULO;
ALTER TABLE DESEMPATADOR RENAME COLUMN TEXTO_CLOB TO MINI_CURRICULO;