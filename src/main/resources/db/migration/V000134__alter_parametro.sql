ALTER TABLE parametro add endereco_servico_tipo VARCHAR2(10 CHAR);

UPDATE PARAMETRO
SET ENDERECO_SERVICO_TIPO='POST'
WHERE NOME='status_destino_quando_nao_tem_desfavoravel';

UPDATE PARAMETRO
SET ENDERECO_SERVICO_TIPO='POST'
WHERE NOME='calendario_campo_dias_entre_criacao_inicio';
