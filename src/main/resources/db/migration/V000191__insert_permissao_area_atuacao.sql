INSERT INTO
    PERMISSAO
        VALUES (51, 30, 'menu_cadastro.menu_area_atuacao:view', 'view', 'menu_area_atuacao', 'Permite visualizar opção de nova Área de Atuação', SYSDATE, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (51, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
