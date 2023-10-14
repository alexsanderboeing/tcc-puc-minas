update status_etapa_acao sea set sea.considera_pendencia = decode(sea.acao_id, 14, 0, 1)
