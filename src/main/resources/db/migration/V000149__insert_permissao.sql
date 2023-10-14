INSERT INTO PERMISSAO VALUES (43, 37, 'menu_configuracoes.menu_parametro:view', 'view', 'menu_parametro', 'Permite visualizar parâmetros', SYSDATE, 1, null, null);
INSERT INTO PERMISSAO VALUES (44, null, 'menu_acessos:view', 'view', 'menu_acesso', 'Permite visualizar opção de acessos', SYSDATE, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (43, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (44, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
