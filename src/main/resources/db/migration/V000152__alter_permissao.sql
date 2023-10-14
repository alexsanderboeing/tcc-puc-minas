DELETE FROM PERFIL_PERMISSAO PP
WHERE PP.PERMISSAO_ID IN (SELECT P.ID FROM PERMISSAO P WHERE P."KEY" = 'perfil_permissionamento:edit');

DELETE FROM PERMISSAO p 
WHERE p."KEY" = 'perfil_permissionamento:edit';

INSERT INTO PERMISSAO VALUES (45, null, 'usuario:view', 'view', 'usuario', 'Permitir visualização do usuário', SYSDATE, 1, null, null);
INSERT INTO PERMISSAO VALUES (46, null, 'usuario:create', 'create', 'usuario', 'Permitir criação de usuário', SYSDATE, 1, null, null);
INSERT INTO PERMISSAO VALUES (47, null, 'usuario:edit', 'edit', 'usuario', 'Permitir edição de usuário', SYSDATE, 1, null, null);
INSERT INTO PERMISSAO VALUES (48, null, 'usuario:delete', 'delete', 'usuario', 'Permitir exclusão de usuário', SYSDATE, 1, null, null);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (45, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (46, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (47, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (48, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
