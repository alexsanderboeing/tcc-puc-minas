alter table contrato_fluxo_processo modify calendario_id null;
alter table contrato_fluxo_processo drop constraint contrato_fluxo_calendario_fk;
