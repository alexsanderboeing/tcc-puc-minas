insert into permissao (id, permission_parent_id, key, type, feature_name, description, created_at, created_by, updated_at, updated_by)
values (53, 30, 'menu_cadastros.menu_auditores:view', 'view', 'menu_auditores', 'Permite visualizar opção de novo auditor', sysdate, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (53, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
