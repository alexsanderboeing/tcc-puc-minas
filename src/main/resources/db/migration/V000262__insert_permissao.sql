INSERT INTO PERMISSAO (ID,PERMISSION_PARENT_ID,"KEY","TYPE",FEATURE_NAME,DESCRIPTION,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES (54,NULL,'lembretes:delete','delete','lembretes','Permitir exclusão do lembrete',TIMESTAMP '2023-01-20 10:25:03.000000',1,NULL,NULL);
INSERT INTO PERMISSAO (ID,PERMISSION_PARENT_ID,"KEY","TYPE",FEATURE_NAME,DESCRIPTION,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES (55,NULL,'lembretes:edit','edit','lembretes','Permitir edição do lembrete',TIMESTAMP '2023-01-20 10:25:03.000000',1,NULL,NULL);
INSERT INTO PERMISSAO (ID,PERMISSION_PARENT_ID,"KEY","TYPE",FEATURE_NAME,DESCRIPTION,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES (56,NULL,'lembretes:create','create','lembretes','Permitir criação do lembrete',TIMESTAMP '2023-01-20 10:25:03.000000',1,NULL,NULL);
INSERT INTO PERMISSAO (ID,PERMISSION_PARENT_ID,"KEY","TYPE",FEATURE_NAME,DESCRIPTION,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES (57,NULL,'lembretes:view','view','lembretes','Permitir visualização do lembrete',TIMESTAMP '2023-01-20 10:25:02.000000',1,NULL,NULL);

INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (54, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (55, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (56, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
INSERT INTO PERFIL_PERMISSAO (PERMISSAO_ID, PERFIL_ID, ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (57, 1, s_perfil_permissao.nextval, SYSDATE, 1, null, null);
