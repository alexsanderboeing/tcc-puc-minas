INSERT INTO
    PERMISSAO
        VALUES (50, 30, 'menu_cadastro.menu_especialidade:view', 'view', 'menu_especialidade', 'Permite visualizar opção de nova Especialidade', SYSDATE, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (50, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
