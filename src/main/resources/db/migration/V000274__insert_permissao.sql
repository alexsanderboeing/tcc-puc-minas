insert into permissao (id, permission_parent_id, key, type, feature_name, description, created_at, created_by, updated_at, updated_by)
values (59, 30, 'menu_cadastros.menu_consultores:view', 'view', 'menu_consultores', 'Permite visualizar opção de novo consultor', sysdate, 1, null, null);

insert into perfil_permissao (id, permissao_id, perfil_id, created_at, created_by, updated_at, updated_by)
values (s_perfil_permissao.nextval, 59, 1, sysdate, 1, sysdate, 1);
