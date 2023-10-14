update STATUS_ETAPA s
	set s.dm_contagem_sla_id = (SELECT id FROM DOMINIO WHERE nome = 'dm_contagem_sla' AND valor = 'P')
		where s.dm_contagem_sla_id = (SELECT id FROM DOMINIO WHERE nome = 'dm_contagem_sla' AND valor = 'C');

delete from DOMINIO d where nome = 'dm_contagem_sla' AND valor = 'C';
