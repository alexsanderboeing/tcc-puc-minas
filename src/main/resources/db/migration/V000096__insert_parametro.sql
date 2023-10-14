insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (1, 'nro_desempatadores_opcoes', 28, null, null, null, 4, null, 'Nro de desempatadores de opções: Parâmetro numérico que irá determinar a quantidade de desempatadores de opções a Operadora para a escolha.', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (2, 'sla_processo_pecentual_alerta', 28, null, null, null, 10, null, 'SLA ? Processo - % do tempo para considerar em alerta: Indicará o % para utilizar no calculo da opção em Alerta', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (3, 'sla_status_pecentual_alerta', 28, null, null, null, 10, null, 'SLA ? Status - % do tempo para considerar em alerta: Indicará o % para utilizar no calculo da opção em Alerta', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (4, 'texto_termo_assinatura_parecer', 29, null, null, null, null, '<br>1. Declaração de não vínculo</b>' || chr(13) ||
                                                                         'Declaro não possuir vínculo de parentesco, ou qualquer outro vínculo com o(a) profissional assistente, o(a) beneficiário(a) e/ou os(as) profissionais médicos(as) empregados;<br>' || chr(13) ||
                                                                         '<b>2. Declaração de isenção</b><br>' || chr(13) ||
                                                                         'Declaro que este parecer está sendo emitido de forma independente e isenta para atender a regulamentação da Agência Nacional de Saúde Suplementar - ANS e/ou dos contratos de planos de saúde firmados com seus(uas) beneficiários(as), relativamente à necessidade de instauração de Junta Médica ou Odontológica para dirimir divergência assistencial.<br>' || chr(13) ||
                                                                         '<b>3. Declaração de respeito as normas</b><br>' || chr(13) ||
                                                                         'Por fim, declaro que para a emissão deste parecer, em conformidade com o Artigo 15º daRN 424 da Agência Nacional de Saúde Suplementar, que para o caso em questão, não houve a necessidade de solicitar documentos complementares, bem como necessidade de avaliar presencialmente o paciente.', 'Texto do termo de assinatura do parecer usando o Editor wysiwyg', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (5, 'status_destino_quando_nao_tem_desfavoravel', 28, 'statusetapa/findAll', null, null, null, null, 'Status destino quando não tem desfavorável: No processo de "Realizar Parecer" e quando o desempatador não registrou nenhum desfavorável, indicar o status de destino para continuar o processo.', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (6, 'texto_confirmacao_solicitacao_processo', 29, null, null, null, null, '<b>Resolução Normativa 424/2017 da ANS – artigo 7</b>:' || chr(13) ||
                                                                                 '“Art. 7º No tocante à cobertura de órteses e próteses ligadas aos atos cirúrgicos listados no Rol de Procedimentos e Eventos em saúde, deverão ser observadas as seguintes disposições:<br>' || chr(13) ||
                                                                                 'I - Cabe ao profissional assistente a prerrogativa de determinar as características (tipo, matéria-prima e dimensões) das órteses, das próteses e dos materiais especiais – OPME necessários à execução dos procedimentos contidos no Rol de Procedimentos e Eventos em Saúde; e' || chr(13) ||
                                                                                 'II - O profissional assistente deve justificar clinicamente a sua indicação e oferecer, pelo menos, 3 (três) marcas de produtos de fabricantes diferentes, quando disponíveis, dentre aquelas regularizadas junto à ANVISA, que atendam às características especificadas.<br>' || chr(13) ||
                                                                                 'Parágrafo único. A operadora deverá instaurar junta médica ou odontológica quando o profissional assistente não indicar as 3 (três) marcas ou a operadora discordar das marcas indicadas”.', 'Texto de confirmação da solicitação do processo usando o Editor wysiwyg', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (7, 'justificativa_padrao_itens_favoraveis_opera', 26, null, 'Não foi apresentado divergência por parte da Operadora para este item', null, null, null, 'Justificativa padrão para itens favoráveis da operadora', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (8, 'valor_padrao_parecer_carater_da_solicitacao', 26, null, 'Trata-se de procedimento eletivo solicitado para paciente com', null, null, null, 'Valor padrão do "Parecer sobre o caráter da solicitação"', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (9, 'texto_documento_aceite_solicitacao_processo', 29, null, null, null, null, null, 'Texto do documento de aceitei da solicitação do processo de junta', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (10, 'calendario_campo_dias_entre_criacao_inicio', 28, 'calendario/findAll', null, null, null, null, 'Calendário para uso na regra de calculo do "Gerar relatório. campo "Dias entre Criação e Inicio:"', null, null, null, null);

insert into parametro (ID, NOME, DM_PARAM_TIPO_VALOR_ID, ENDERECO_SERVICO, VALOR_STRING, VALOR_DATE, VALOR_NUMBER, VALOR_CLOB, EXPLICACAO, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
values (11, 'tempo_em_dias_limpeza_anexo', 28, null, null, null, 1825, null, 'Armazena o tempo que o arquivo vai ficar disponivel no zstorage', null, null, null, null);
