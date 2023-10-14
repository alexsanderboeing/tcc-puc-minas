insert into acao (id, nome, nome_botao, cor_botao, ordem_em_tela, dm_modelo_tela_id, created_at, created_by, updated_at, updated_by)
values (s_acao.nextval, 'Solicitar Revisão', 'Solicitar Revisão', '#FF0000', (select max(ordem_em_tela) from acao) + 1, 1, sysdate, 1, null, null);

insert into acao (id, nome, nome_botao, cor_botao, ordem_em_tela, dm_modelo_tela_id, created_at, created_by, updated_at, updated_by)
values (s_acao.nextval, 'Enviar Comunicado de Finalização', 'Enviar Comunicado de Finalização', '#0000FF', (select max(ordem_em_tela) from acao) + 1, 1, sysdate, 1, null, null);

insert into acao (id, nome, nome_botao, cor_botao, ordem_em_tela, dm_modelo_tela_id, created_at, created_by, updated_at, updated_by)
values (s_acao.nextval, 'Enviar Parecer para Operadora', 'Enviar Parecer para Operadora', '#0000FF', (select max(ordem_em_tela) from acao) + 1, 1, sysdate, 1, null, null);
