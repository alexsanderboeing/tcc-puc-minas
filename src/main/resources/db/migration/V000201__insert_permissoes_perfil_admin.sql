DELETE
    FROM PERFIL_PERMISSAO pp
        WHERE pp.PERMISSAO_ID = (SELECT id FROM PERMISSAO p WHERE p.KEY = 'menu_cadastro.menu_especialidade:view')
            AND pp.PERFIL_ID = (SELECT id FROM perfil p WHERE p.TYPE = 'admin');

DELETE
    FROM PERFIL_PERMISSAO pp
        WHERE pp.PERMISSAO_ID = (SELECT id FROM PERMISSAO p WHERE p.KEY = 'menu_cadastro.menu_area_atuacao:view')
            AND pp.PERFIL_ID = (SELECT id FROM perfil p WHERE p.TYPE = 'admin');

DELETE
    FROM PERFIL_PERMISSAO pp
        WHERE pp.PERMISSAO_ID = (SELECT id FROM PERMISSAO p WHERE p.KEY = 'menu_cadastro.menu_banco:view')
            AND pp.PERFIL_ID = (SELECT id FROM perfil p WHERE p.TYPE = 'admin');

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY)
    VALUES (
            (SELECT id FROM PERMISSAO p WHERE p.KEY = 'menu_cadastro.menu_especialidade:view'),
            (SELECT id FROM perfil p WHERE p.TYPE = 'admin'),
            s_perfil_permissao.nextval, SYSDATE, 1);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY)
    VALUES (
            (SELECT id FROM PERMISSAO p WHERE p.KEY = 'menu_cadastro.menu_area_atuacao:view'),
            (SELECT id FROM perfil p WHERE p.TYPE = 'admin'),
            s_perfil_permissao.nextval, SYSDATE, 1);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY)
    VALUES (
            (SELECT id FROM PERMISSAO p WHERE p.KEY = 'menu_cadastro.menu_banco:view'),
            (SELECT id FROM perfil p WHERE p.TYPE = 'admin'),
            s_perfil_permissao.nextval, SYSDATE, 1);
