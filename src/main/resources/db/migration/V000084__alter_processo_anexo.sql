alter table processo_anexo drop constraint processo_anexo_pk;

alter table processo_anexo drop column id;

drop sequence s_processo_anexo;
