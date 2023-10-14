alter table USUARIO add constraint USUARIO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table USUARIO add constraint USUARIO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table USUARIO_RESET_TOKEN add constraint USUARIO_RESET_TOKEN_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table USUARIO_RESET_TOKEN add constraint USUARIO_RESET_TOKEN_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table FAILED_LOGIN add constraint FAILED_LOGIN_USUARIO_ID_FK foreign key (USUARIO_ID) references USUARIO (ID);

alter table PERFIL add constraint PERFIL_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PERFIL add constraint PERFIL_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table USUARIO_PERFIL add constraint USUARIO_PERFIL_USUARIO_ID_FK foreign key (USUARIO_ID) references USUARIO (ID);
alter table USUARIO_PERFIL add constraint USUARIO_PERFIL_PERFIL_ID_FK foreign key (PERFIL_ID) references PERFIL (ID);
alter table USUARIO_PERFIL add constraint USUARIO_PERFIL_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table USUARIO_PERFIL add constraint USUARIO_PERFIL_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PAPEL_PERFIL add constraint PAPEL_PERFIL_PERFIL_ID_FK foreign key (PERFIL_ID) references PERFIL (ID);
alter table PAPEL_PERFIL add constraint PAPEL_PERFIL_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PAPEL_PERFIL add constraint PAPEL_PERFIL_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PERFIL_GRID add constraint PERFIL_GRID_USUARIO_ID_FK foreign key (USUARIO_ID) references USUARIO (ID);
alter table PERFIL_GRID add constraint PERFIL_GRID_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PERFIL_GRID add constraint PERFIL_GRID_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PERMISSAO add constraint PERMISSAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PERMISSAO add constraint PERMISSAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PERFIL_PERMISSAO add constraint PERFIL_PERMISSAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PERFIL_PERMISSAO add constraint PERFIL_PERMISSAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PAPEL add constraint PAPEL_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PAPEL add constraint PAPEL_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table DOMINIO add constraint DOMINIO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table DOMINIO add constraint DOMINIO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PARAMETRO add constraint PARAMETRO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PARAMETRO add constraint PARAMETRO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONTATO add constraint CONTATO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONTATO add constraint CONTATO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table DESEMPATADOR add constraint DESEMPATADOR_USUARIO_ID_FK foreign key (USUARIO_ID) references USUARIO (ID);
alter table DESEMPATADOR add constraint DESEMPATADOR_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table DESEMPATADOR add constraint DESEMPATADOR_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table DESEMPATADOR_FAVORITO add constraint DESEMPATADOR_FAVORITO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table DESEMPATADOR_FAVORITO add constraint DESEMPATADOR_FAVORITO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table DESEMPATADOR_PRECO add constraint DESEMPATADOR_PRECO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table DESEMPATADOR_PRECO add constraint DESEMPATADOR_PRECO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table DESEMPATADOR_CBOS add constraint DESEMPATADOR_CBOS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table DESEMPATADOR_CBOS add constraint DESEMPATADOR_CBOS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table DESEMPATADOR_CONSELHO add constraint DESEMPATADOR_CONSELHO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table DESEMPATADOR_CONSELHO add constraint DESEMPATADOR_CONSELHO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table EQUIPE add constraint EQUIPE_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table EQUIPE add constraint EQUIPE_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table EQUIPE_MEMBRO add constraint EQUIPE_MEMBRO_USUARIO_EXT_ID_FK foreign key (USUARIO_EXT_ID) references USUARIO (ID);
alter table EQUIPE_MEMBRO add constraint EQUIPE_MEMBRO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table EQUIPE_MEMBRO add constraint EQUIPE_MEMBRO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table EQUIPE_MEMBRO_INATIVIDADE add constraint EQUIPE_MEMBRO_INATIVIDADE_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table EQUIPE_MEMBRO_INATIVIDADE add constraint EQUIPE_MEMBRO_INATIVIDADE_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table STATUS_ETAPA add constraint STATUS_ETAPA_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table STATUS_ETAPA add constraint STATUS_ETAPA_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table STATUS_ETAPA_ACAO add constraint STATUS_ETAPA_ACAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table STATUS_ETAPA_ACAO add constraint STATUS_ETAPA_ACAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table STATUS_ETAPA_ACAO_NOTIF add constraint STATUS_ETAPA_ACAO_NOTIF_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table STATUS_ETAPA_ACAO_NOTIF add constraint STATUS_ETAPA_ACAO_NOTIF_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table STATUS_ETAPA_ACAO_PERFIL add constraint STATUS_ETAPA_ACAO_PERFIL_PERFIL_ID_FK foreign key (PERFIL_ID) references PERFIL (ID);
alter table STATUS_ETAPA_ACAO_PERFIL add constraint STATUS_ETAPA_ACAO_PERFIL_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table STATUS_ETAPA_ACAO_PERFIL add constraint STATUS_ETAPA_ACAO_PERFIL_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CALENDARIO add constraint CALENDARIO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CALENDARIO add constraint CALENDARIO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CALENDARIO_FERIADOS add constraint CALENDARIO_FERIADOS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CALENDARIO_FERIADOS add constraint CALENDARIO_FERIADOS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CALENDARIO_DIAS_HORAS add constraint CALENDARIO_DIAS_HORAS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);
alter table CALENDARIO_DIAS_HORAS add constraint CALENDARIO_DIAS_HORAS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);

alter table CONSULTOR add constraint CONSULTOR_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONSULTOR add constraint CONSULTOR_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONSULTOR_CONSELHO add constraint CONSULTOR_CONSELHO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONSULTOR_CONSELHO add constraint CONSULTOR_CONSELHO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONSULTOR_CBOS add constraint CONSULTOR_CBOS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONSULTOR_CBOS add constraint CONSULTOR_CBOS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table AUDITOR add constraint AUDITOR_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table AUDITOR add constraint AUDITOR_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table AUDITOR_CBOS add constraint AUDITOR_CBOS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table AUDITOR_CBOS add constraint AUDITOR_CBOS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table AUDITOR_CONSELHO add constraint AUDITOR_CONSELHO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table AUDITOR_CONSELHO add constraint AUDITOR_CONSELHO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table BENEFICIARIO add constraint BENEFICIARIO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table BENEFICIARIO add constraint BENEFICIARIO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table BENEFICIARIO_ANEXO add constraint BENEFICIARIO_ANEXO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table BENEFICIARIO_ANEXO add constraint BENEFICIARIO_ANEXO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO add constraint PROCESSO_ADMINISTRATIVO_FESC_EXT_ID_FK foreign key (ADMINISTRATIVO_FESC_EXT_ID) references USUARIO (ID);
alter table PROCESSO add constraint PROCESSO_AUDITOR_QUALIDADE_EXT_ID_FK foreign key (AUDITOR_QUALIDADE_EXT_ID) references USUARIO (ID);
alter table PROCESSO add constraint PROCESSO_ADMINISTRATIVO_OPERADORA_ID_FK foreign key (ADMINISTRATIVO_OPERADORA_ID) references USUARIO (ID);
alter table PROCESSO add constraint PROCESSO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO add constraint PROCESSO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO_HIST_NOTIFICACAO add constraint PROCESSO_HIST_NOTIFICACAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO_HIST_NOTIFICACAO add constraint PROCESSO_HIST_NOTIFICACAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO_DESEMPATADORES add constraint PROCESSO_DESEMPATADORES_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO_DESEMPATADORES add constraint PROCESSO_DESEMPATADORES_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO_ETAPA add constraint PROCESSO_ETAPA_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO_ETAPA add constraint PROCESSO_ETAPA_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO_ANEXO add constraint PROCESSO_ANEXO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO_ANEXO add constraint PROCESSO_ANEXO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO_HISTORICO add constraint PROCESSO_HISTORICO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO_HISTORICO add constraint PROCESSO_HISTORICO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROCESSO_ITEM add constraint PROCESSO_ITEM_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROCESSO_ITEM add constraint PROCESSO_ITEM_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table FLUXO_PROCESSO add constraint FLUXO_PROCESSO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table FLUXO_PROCESSO add constraint FLUXO_PROCESSO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONTRATO_FLUXO_PROCESSO add constraint CONTRATO_FLUXO_PROCESSO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONTRATO_FLUXO_PROCESSO add constraint CONTRATO_FLUXO_PROCESSO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table ANEXO add constraint ANEXO_USUARIO_ID_FK foreign key (USUARIO_ID) references USUARIO (ID);
alter table ANEXO add constraint ANEXO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table ANEXO add constraint ANEXO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CLASSIFICACAO_ANEXO add constraint CLASSIFICACAO_ANEXO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CLASSIFICACAO_ANEXO add constraint CLASSIFICACAO_ANEXO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROFISSIONAL_ASSISTENTE add constraint PROFISSIONAL_ASSISTENTE_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROFISSIONAL_ASSISTENTE add constraint PROFISSIONAL_ASSISTENTE_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROFISSIONAL_ASSIS_ANEXO add constraint PROFISSIONAL_ASSIS_ANEXO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROFISSIONAL_ASSIS_ANEXO add constraint PROFISSIONAL_ASSIS_ANEXO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROFISSIONAL_ASSIST_CONSELHO add constraint PROFISSIONAL_ASSIST_CONSELHO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROFISSIONAL_ASSIST_CONSELHO add constraint PROFISSIONAL_ASSIST_CONSELHO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table PROFISSIONAL_ASSIS_CBOS add constraint PROFISSIONAL_ASSIS_CBOS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table PROFISSIONAL_ASSIS_CBOS add constraint PROFISSIONAL_ASSIS_CBOS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONTRATO add constraint CONTRATO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONTRATO add constraint CONTRATO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONTRATO_PRECO add constraint CONTRATO_PRECO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONTRATO_PRECO add constraint CONTRATO_PRECO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONTRATO_ADITIVO add constraint CONTRATO_ADITIVO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONTRATO_ADITIVO add constraint CONTRATO_ADITIVO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CONTRATO_OPERADORAS add constraint CONTRATO_OPERADORAS_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CONTRATO_OPERADORAS add constraint CONTRATO_OPERADORAS_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table NOTIFICACAO add constraint NOTIFICACAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table NOTIFICACAO add constraint NOTIFICACAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table ENDERECO add constraint ENDERECO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table ENDERECO add constraint ENDERECO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CHAT_MENSAGEM add constraint CHAT_MENSAGEM_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CHAT_MENSAGEM add constraint CHAT_MENSAGEM_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CHAT_MENSAGEM_LIDA add constraint CHAT_MENSAGEM_LIDA_USUARIO_EXT_ID_FK foreign key (USUARIO_EXT_ID) references USUARIO (ID);
alter table CHAT_MENSAGEM_LIDA add constraint CHAT_MENSAGEM_LIDA_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CHAT_MENSAGEM_LIDA add constraint CHAT_MENSAGEM_LIDA_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table MODELO_CARTA add constraint MODELO_CARTA_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table MODELO_CARTA add constraint MODELO_CARTA_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table MODELO_CARTA_NOTIFICACAO add constraint MODELO_CARTA_NOTIFICACAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table MODELO_CARTA_NOTIFICACAO add constraint MODELO_CARTA_NOTIFICACAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table OPERADORA add constraint OPERADORA_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table OPERADORA add constraint OPERADORA_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table OPERADORA_USUARIO add constraint OPERADORA_USUARIO_USUARIO_EXT_ID_FK foreign key (USUARIO_EXT_ID) references USUARIO (ID);
alter table OPERADORA_USUARIO add constraint OPERADORA_USUARIO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table OPERADORA_USUARIO add constraint OPERADORA_USUARIO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table ACAO add constraint ACAO_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table ACAO add constraint ACAO_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);

alter table CLASSIFICACAO_INTERNA add constraint CLASSIFICACAO_INTERNA_CREATED_BY_FK foreign key (CREATED_BY) references USUARIO (ID);
alter table CLASSIFICACAO_INTERNA add constraint CLASSIFICACAO_INTERNA_UPDATED_BY_FK foreign key (UPDATED_BY) references USUARIO (ID);
