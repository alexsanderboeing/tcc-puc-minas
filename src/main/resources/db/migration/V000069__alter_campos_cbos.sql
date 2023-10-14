alter table auditor_cbos rename column cbos_ext_id to cbos_id;

alter table auditor_cbos drop constraint auditor_cbos_uk cascade;
alter table auditor_cbos add constraint auditor_cbos_uk unique (auditor_id, cbos_id);

alter table desempatador_cbo rename column cbos_ext_id to cbos_id;
alter table desempatador_cbo drop constraint DESEMPATADOR_CBO_ESPEC_UK cascade;
alter table desempatador_cbo add constraint desempatador_cbo_uk unique (DESEMPATADOR_ID, cbos_id);

rename desempatador_cbo to desempatador_cbos;

alter table consultor_cbos rename column cbos_ext_id to cbos_id;
alter table consultor_cbos add constraint consultor_cbos_uk unique (consultor_id, cbos_id);

alter table profissional_assis_cbos rename column cbos_ext_id to cbos_id;
alter table profissional_assis_cbos drop constraint PROFISSIONAL_ASSIS_CBOS_UK cascade;
alter table profissional_assis_cbos add constraint PROFISSIONAL_ASSIS_CBOS_UK
    unique (profissional_assistente_id, cbos_id);

