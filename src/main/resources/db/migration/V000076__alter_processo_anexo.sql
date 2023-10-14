ALTER TABLE processo_anexo rename column arquivo_id to anexo_id;
ALTER TABLE processo_anexo drop constraint processo_anexo_uk;
ALTER TABLE processo_anexo ADD CONSTRAINT processo_anexo_uk UNIQUE ( anexo_id, processo_id );
