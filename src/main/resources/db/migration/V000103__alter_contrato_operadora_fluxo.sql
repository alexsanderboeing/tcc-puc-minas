ALTER TABLE contrato_fluxo_processo ADD CONSTRAINT contrato_fluxo_processo_uk UNIQUE (fluxo_processo_id, contrato_id);
ALTER TABLE contrato_operadoras ADD CONSTRAINT contrato_operadora_uk UNIQUE (operadora_id);
