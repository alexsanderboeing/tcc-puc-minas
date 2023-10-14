insert into permissao (id, permission_parent_id, key, type, feature_name, description, created_at, created_by, updated_at, updated_by)
values (58, null, 'dados_processo.criar_notificacao:create', 'create', 'dados_processo', 'Permitir a criação de uma notificação, via dados do processo', sysdate, 1, null, null);

insert into perfil_permissao (id, permissao_id, perfil_id, created_at, created_by, updated_at, updated_by)
values (s_perfil_permissao.nextval, 58, 1, sysdate, 1, null, null);
