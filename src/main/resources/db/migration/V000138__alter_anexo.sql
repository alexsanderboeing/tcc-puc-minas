alter table ANEXO add EXTENSAO VARCHAR2(100);

update anexo set nome = regexp_replace(nome,'\.[^\.]*$'), extensao = substr(regexp_substr(nome, '\.[^\.]*$'), 2);