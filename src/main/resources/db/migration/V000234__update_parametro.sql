UPDATE PARAMETRO SET PARAMETRO_ADMINISTRATIVO = 0 WHERE NOME <> 'indicacao_tempo_diario_hrs_trabalho';

UPDATE PARAMETRO SET PARAMETRO_ADMINISTRATIVO = 1 WHERE NOME = 'indicacao_tempo_diario_hrs_trabalho';
