INSERT INTO PERMISSAO VALUES (49, 37, 'menu_configuracoes.menu_log_auditoria:view', 'view', 'menu_log_auditoria', 'Permite visualizar logs de auditoria', SYSDATE, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (49, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
