alter table beneficiario_anexo drop constraint beneficiario_anexo_pk;

alter table beneficiario_anexo drop column id;

drop sequence s_beneficiario_anexo;
