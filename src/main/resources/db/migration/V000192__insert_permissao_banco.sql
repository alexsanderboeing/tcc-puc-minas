INSERT INTO
    PERMISSAO
        VALUES (52, 30, 'menu_cadastro.menu_banco:view', 'view', 'menu_banco', 'Permite visualizar opção de novo Banco', SYSDATE, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (52, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
