ALTER TABLE anexo
    ADD CONSTRAINT anexo_classificacao_anexo_fk FOREIGN KEY ( classificacao_anexo_id )
        REFERENCES classificacao_anexo ( id );

ALTER TABLE auditor_cbos
    ADD CONSTRAINT auditor_cbos_auditor_fk FOREIGN KEY ( auditor_id )
        REFERENCES auditor ( id );

ALTER TABLE auditor_conselho
    ADD CONSTRAINT auditor_conselho_auditor_fk FOREIGN KEY ( auditor_id )
        REFERENCES auditor ( id );

ALTER TABLE auditor
    ADD CONSTRAINT auditor_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE beneficiario_anexo
    ADD CONSTRAINT beneficiario_anexo_anexo_fk FOREIGN KEY ( anexo_id )
        REFERENCES anexo ( id );

ALTER TABLE beneficiario_anexo
    ADD CONSTRAINT beneficiario_anexo_fk FOREIGN KEY ( beneficiario_id )
        REFERENCES beneficiario ( id );

ALTER TABLE beneficiario
    ADD CONSTRAINT beneficiario_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE calendario_dias_horas
    ADD CONSTRAINT calendario_dias_horas_fk FOREIGN KEY ( calendario_id )
        REFERENCES calendario ( id );

ALTER TABLE calendario_feriados
    ADD CONSTRAINT calendario_feriados_fk FOREIGN KEY ( calendario_id )
        REFERENCES calendario ( id );

ALTER TABLE chat_mensagem
    ADD CONSTRAINT chat_mensagem_anexo_fk FOREIGN KEY ( anexo_id )
        REFERENCES anexo ( id );

ALTER TABLE chat_mensagem
    ADD CONSTRAINT chat_papel_fk FOREIGN KEY ( papel_id )
        REFERENCES papel ( id );

ALTER TABLE chat_mensagem
    ADD CONSTRAINT chat_processo_etapa_fk FOREIGN KEY ( processo_etapa_id )
        REFERENCES processo_etapa ( id );

ALTER TABLE chat_mensagem
    ADD CONSTRAINT chat_processo_fk FOREIGN KEY ( processo_id )
        REFERENCES processo ( id );

ALTER TABLE chat_mensagem
    ADD CONSTRAINT chat_status_etapa_fk FOREIGN KEY ( status_etapa_id )
        REFERENCES status_etapa ( id );

ALTER TABLE consultor_cbos
    ADD CONSTRAINT consultor_cbos_fk FOREIGN KEY ( consultor_id )
        REFERENCES consultor ( id );

ALTER TABLE consultor_conselho
    ADD CONSTRAINT consultor_conselho_fk FOREIGN KEY ( consultor_id )
        REFERENCES consultor ( id );

ALTER TABLE consultor
    ADD CONSTRAINT consultor_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE contato
    ADD CONSTRAINT contato_beneficiario_fk FOREIGN KEY ( beneficiario_id )
        REFERENCES beneficiario ( id );

ALTER TABLE contato
    ADD CONSTRAINT contato_desempatador_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE contato
    ADD CONSTRAINT contato_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE contato
    ADD CONSTRAINT contato_profissional_fk FOREIGN KEY ( profissional_assistente_id )
        REFERENCES profissional_assistente ( id );

ALTER TABLE contrato_aditivo
    ADD CONSTRAINT contrato_aditivo_contrato_fk FOREIGN KEY ( contrato_id )
        REFERENCES contrato ( id );

ALTER TABLE contrato_fluxo_processo
    ADD CONSTRAINT contrato_fluxo_calendario_fk FOREIGN KEY ( calendario_id )
        REFERENCES calendario ( id );

ALTER TABLE contrato_fluxo_processo
    ADD CONSTRAINT contrato_fluxo_processo_fk FOREIGN KEY ( contrato_id )
        REFERENCES contrato ( id );

ALTER TABLE contrato
    ADD CONSTRAINT contrato_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE contrato_preco
    ADD CONSTRAINT contrato_preco_contrato_fk FOREIGN KEY ( contrato_id )
        REFERENCES contrato ( id );

ALTER TABLE desempatador_favorito
    ADD CONSTRAINT desem_fav_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE desempatador_conselho
    ADD CONSTRAINT desempatador_conselho_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE desempatador_cbo
    ADD CONSTRAINT desempatador_especialidade_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE desempatador_favorito
    ADD CONSTRAINT desempatador_favorito_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE desempatador_preco
    ADD CONSTRAINT desempatador_preco_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE processo
    ADD CONSTRAINT dm_cancelamento_motivado_fk FOREIGN KEY ( dm_cancelamento_motivado_id )
        REFERENCES dominio ( id );

ALTER TABLE processo
    ADD CONSTRAINT dm_carater_atendimento_fk FOREIGN KEY ( dm_carater_atendimento_id )
        REFERENCES dominio ( id );

ALTER TABLE status_etapa
    ADD CONSTRAINT dm_contagem_sla_fk FOREIGN KEY ( dm_contagem_sla_id )
        REFERENCES dominio ( id );

ALTER TABLE calendario_dias_horas
    ADD CONSTRAINT dm_dia_semana_fk FOREIGN KEY ( dm_dia_semana_id )
        REFERENCES dominio ( id );

ALTER TABLE contrato
    ADD CONSTRAINT dm_indice_reajuste_fk FOREIGN KEY ( dm_indice_reajuste_id )
        REFERENCES dominio ( id );

ALTER TABLE acao
    ADD CONSTRAINT dm_modelo_tela_fk FOREIGN KEY ( dm_modelo_tela_id )
        REFERENCES dominio ( id );

ALTER TABLE processo
    ADD CONSTRAINT dm_motivo_cancel_processo_fk FOREIGN KEY ( dm_motivo_cancel_processo_id )
        REFERENCES dominio ( id );

ALTER TABLE processo_item
    ADD CONSTRAINT dm_parecer_desemp_rascunho_fk FOREIGN KEY ( dm_parecer_desemp_rascunho_id )
        REFERENCES dominio ( id );

ALTER TABLE processo_item
    ADD CONSTRAINT dm_parecer_desempatador_fk FOREIGN KEY ( dm_parecer_desempatador_id )
        REFERENCES dominio ( id );

ALTER TABLE processo_item
    ADD CONSTRAINT dm_parecer_operadora_fk FOREIGN KEY ( dm_parecer_operadora_id )
        REFERENCES dominio ( id );

ALTER TABLE beneficiario
    ADD CONSTRAINT dm_sexo_beneficiario_fk FOREIGN KEY ( dm_sexo_beneficiario_id )
        REFERENCES dominio ( id );

ALTER TABLE desempatador_conselho
    ADD CONSTRAINT dm_tipo_conselho_fk FOREIGN KEY ( dm_tipo_conselho_id )
        REFERENCES dominio ( id );

ALTER TABLE profissional_assist_conselho
    ADD CONSTRAINT dm_tipo_conselho_fkv1 FOREIGN KEY ( dm_tipo_conselho_id )
        REFERENCES dominio ( id );

ALTER TABLE consultor_conselho
    ADD CONSTRAINT dm_tipo_conselho_fkv2 FOREIGN KEY ( dm_tipo_conselho_id )
        REFERENCES dominio ( id );

ALTER TABLE contato
    ADD CONSTRAINT dm_tipo_contato_fkv1 FOREIGN KEY ( dm_tipo_contato_id )
        REFERENCES dominio ( id );

ALTER TABLE processo_item
    ADD CONSTRAINT dm_tipo_item_processo_fk FOREIGN KEY ( dm_tipo_item_processo_id )
        REFERENCES dominio ( id );

ALTER TABLE fluxo_processo
    ADD CONSTRAINT dm_tipo_processo_fk FOREIGN KEY ( dm_tipo_processo_id )
        REFERENCES dominio ( id );

ALTER TABLE parametro
    ADD CONSTRAINT dm_tipo_valor_fk FOREIGN KEY ( dm_param_tipo_valor_id )
        REFERENCES dominio ( id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_beneficiario_fk FOREIGN KEY ( beneficiario_id )
        REFERENCES beneficiario ( id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_profissional_fk FOREIGN KEY ( profissional_assistente_id )
        REFERENCES profissional_assistente ( id );

ALTER TABLE equipe_membro_inatividade
    ADD CONSTRAINT equipe_membro_inat_fk FOREIGN KEY ( equipe_membro_id )
        REFERENCES equipe_membro ( id );

ALTER TABLE equipe_membro
    ADD CONSTRAINT equipe_membros_fk FOREIGN KEY ( equipe_trabalho_id )
        REFERENCES equipe ( id );

ALTER TABLE modelo_carta
    ADD CONSTRAINT modelo_carta_anexo_fk FOREIGN KEY ( anexo_id )
        REFERENCES anexo ( id );

ALTER TABLE modelo_carta_notificacao
    ADD CONSTRAINT modelo_carta_notif_fk FOREIGN KEY ( modelo_carta_id )
        REFERENCES modelo_carta ( id );

ALTER TABLE modelo_carta_notificacao
    ADD CONSTRAINT modelo_carta_notif_fkv1 FOREIGN KEY ( notificacao_id )
        REFERENCES notificacao ( id );

ALTER TABLE modelo_carta_notificacao
    ADD CONSTRAINT modelo_carta_notif_fkv2 FOREIGN KEY ( papel_id )
        REFERENCES papel ( id );

ALTER TABLE modelo_carta
    ADD CONSTRAINT modelo_carta_processo_etapa_fk FOREIGN KEY ( processo_etapa_id )
        REFERENCES processo_etapa ( id );

ALTER TABLE contrato_fluxo_processo
    ADD CONSTRAINT operador_fluxo_fk FOREIGN KEY ( fluxo_processo_id )
        REFERENCES fluxo_processo ( id );

ALTER TABLE operadora
    ADD CONSTRAINT operadora_equipe_trabalho_fk FOREIGN KEY ( equipe_administrativo_fesc_id )
        REFERENCES equipe ( id );

ALTER TABLE operadora
    ADD CONSTRAINT operadora_equipe_trabalho_fkv2 FOREIGN KEY ( equipe_auditor_qualidade_id )
        REFERENCES equipe ( id );

ALTER TABLE operadora_usuario
    ADD CONSTRAINT operadora_usuario_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE processo_anexo
    ADD CONSTRAINT processo_anexos_arquivo_fk FOREIGN KEY ( arquivo_id )
        REFERENCES anexo ( id );

ALTER TABLE processo_anexo
    ADD CONSTRAINT processo_anexos_processo_fk FOREIGN KEY ( processo_id )
        REFERENCES processo ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_auditor_fk FOREIGN KEY ( auditor_id )
        REFERENCES auditor ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_beneficiario_fk FOREIGN KEY ( beneficiario_id )
        REFERENCES beneficiario ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_classificacao_fk FOREIGN KEY ( classificacao_interna_id )
        REFERENCES classificacao_interna ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_consultor_fk FOREIGN KEY ( consultor_id )
        REFERENCES consultor ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_contrato_fk FOREIGN KEY ( contrato_id )
        REFERENCES contrato ( id );

ALTER TABLE processo_desempatadores
    ADD CONSTRAINT processo_desempata_fk FOREIGN KEY ( processo_id )
        REFERENCES processo ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_desempatador_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE processo_desempatadores
    ADD CONSTRAINT processo_desempatadores_fk FOREIGN KEY ( desempatador_id )
        REFERENCES desempatador ( id );

ALTER TABLE processo_etapa
    ADD CONSTRAINT processo_etapa_fluxo_fk FOREIGN KEY ( fluxo_processo_id )
        REFERENCES fluxo_processo ( id );

ALTER TABLE processo_hist_notificacao
    ADD CONSTRAINT processo_his_notif_anexo_fk FOREIGN KEY ( anexo_id )
        REFERENCES anexo ( id );

ALTER TABLE processo_hist_notificacao
    ADD CONSTRAINT processo_his_notif_chat_fk FOREIGN KEY ( chat_mensagem_id )
        REFERENCES chat_mensagem ( id );

ALTER TABLE processo_hist_notificacao
    ADD CONSTRAINT processo_his_notif_fk FOREIGN KEY ( processo_id )
        REFERENCES processo ( id );

ALTER TABLE processo_historico
    ADD CONSTRAINT processo_his_processo_fk FOREIGN KEY ( processo_id )
        REFERENCES processo ( id );

ALTER TABLE processo_historico
    ADD CONSTRAINT processo_his_status_fk FOREIGN KEY ( status_etapa_id )
        REFERENCES status_etapa ( id );

ALTER TABLE processo_historico
    ADD CONSTRAINT processo_hist_etapa_fk FOREIGN KEY ( processo_etapa_id )
        REFERENCES processo_etapa ( id );

ALTER TABLE processo_hist_notificacao
    ADD CONSTRAINT processo_hist_notif_fk FOREIGN KEY ( processo_etapa_id )
        REFERENCES processo_etapa ( id );

ALTER TABLE processo_hist_notificacao
    ADD CONSTRAINT processo_hist_notif_fkv1 FOREIGN KEY ( notificacao_id )
        REFERENCES notificacao ( id );

ALTER TABLE processo_hist_notificacao
    ADD CONSTRAINT processo_hist_notif_papel_fk FOREIGN KEY ( papel_id )
        REFERENCES papel ( id );

ALTER TABLE processo_item
    ADD CONSTRAINT processo_item_processo_fk FOREIGN KEY ( processo_id )
        REFERENCES processo ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_operadora_cont_fk FOREIGN KEY ( operadora_contratante_id )
        REFERENCES operadora ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_operadora_origem_fk FOREIGN KEY ( operadora_origem_id )
        REFERENCES operadora ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_operadora_solic_fk FOREIGN KEY ( operadora_solicitante_id )
        REFERENCES operadora ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_prof_fk FOREIGN KEY ( profissional_assistente_id )
        REFERENCES profissional_assistente ( id );

ALTER TABLE processo
    ADD CONSTRAINT processo_status_etapa_fk FOREIGN KEY ( status_etapa_id )
        REFERENCES status_etapa ( id );

ALTER TABLE profissional_assis_anexo
    ADD CONSTRAINT profissiona_anexo_fk FOREIGN KEY ( profissional_assistente_id )
        REFERENCES profissional_assistente ( id );

ALTER TABLE profissional_assis_anexo
    ADD CONSTRAINT profissional_anexo_fk FOREIGN KEY ( anexo_id )
        REFERENCES anexo ( id );

ALTER TABLE profissional_assist_conselho
    ADD CONSTRAINT profissional_assist_cons_fk FOREIGN KEY ( profissional_assistente_id )
        REFERENCES profissional_assistente ( id );

ALTER TABLE profissional_assis_cbos
    ADD CONSTRAINT profissional_cbos_fk FOREIGN KEY ( profissional_assistente_id )
        REFERENCES profissional_assistente ( id );

ALTER TABLE profissional_assistente
    ADD CONSTRAINT profissional_operadora_fk FOREIGN KEY ( operadora_id )
        REFERENCES operadora ( id );

ALTER TABLE status_etapa_acao
    ADD CONSTRAINT status_acao_detinofk FOREIGN KEY ( status_etapa_destino_id )
        REFERENCES status_etapa ( id );

ALTER TABLE status_etapa_acao
    ADD CONSTRAINT status_acao_fkv1 FOREIGN KEY ( status_etapa_id )
        REFERENCES status_etapa ( id );

ALTER TABLE status_etapa_acao_notif
    ADD CONSTRAINT status_acao_notif_fk FOREIGN KEY ( notificacao_id )
        REFERENCES notificacao ( id );

ALTER TABLE status_etapa_acao_notif
    ADD CONSTRAINT status_acao_notif_fkv1 FOREIGN KEY ( status_etapa_acao_id )
        REFERENCES status_etapa_acao ( id );

ALTER TABLE status_etapa_acao_notif
    ADD CONSTRAINT status_acao_notif_papel_fk FOREIGN KEY ( papel_id )
        REFERENCES papel ( id );

ALTER TABLE status_etapa_acao_perfil
    ADD CONSTRAINT status_acao_perfil_fk FOREIGN KEY ( status_etapa_acao_id )
        REFERENCES status_etapa_acao ( id );

ALTER TABLE status_etapa_acao
    ADD CONSTRAINT status_etapa_acao_fk FOREIGN KEY ( acao_id )
        REFERENCES acao ( id );

ALTER TABLE status_etapa
    ADD CONSTRAINT status_etapa_calendario_fk FOREIGN KEY ( calendario_id )
        REFERENCES calendario ( id );

ALTER TABLE status_etapa
    ADD CONSTRAINT status_etapa_papel_fk FOREIGN KEY ( restringe_papel_id )
        REFERENCES papel ( id );

ALTER TABLE status_etapa
    ADD CONSTRAINT status_processo_fk FOREIGN KEY ( processo_etapa_id )
        REFERENCES processo_etapa ( id );

ALTER TABLE auditor_conselho
    ADD CONSTRAINT tipo_conselho_fk FOREIGN KEY ( tipo_conselho_id )
        REFERENCES dominio ( id );
