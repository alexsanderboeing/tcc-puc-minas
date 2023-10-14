ALTER TABLE PARAMETRO rename column nome_dominio to ENDERECO_SERVICO;
ALTER TABLE PARAMETRO modify endereco_servico VARCHAR2(100 CHAR);
