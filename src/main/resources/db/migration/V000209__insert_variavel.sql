insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (1, '${data_e_hora}', 'Data e hora', 'Data e hora no formato dd/mm/aaaa', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (2, '${data}', 'Data', 'Data no formato dd/mm/aaaa', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (3, '${numero_processo}', 'Número do processo', 'Dados do processo', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (4, '${numero_pedido_guia}', 'Número do pedido da guia', 'Dados do processo — Número do pedido da guia', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (5, '${parecer_final_desempatador}', 'Parecer final do desempatador', 'Dados do processo — Parecer final do desempatador', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (6, '${resumo_ao_beneficiario}', 'Resumo ao beneficiário', 'Dados do processo — Resumo ao beneficiário', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (7, '${prazo_inicial_ans}', 'Prazo inicial da ANS', 'Dados do processo — Data no formato dd/mm/aaaa da data inicial do processo ans', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (8, '${prazo_final_ans}', 'Prazo inicial da ANS', 'Dados do processo — Data no formato dd/mm/aaaa da data final do processo ans', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (9, '${carater_atendimento}', 'Carater de atendimento', 'Dados do processo — descrição do carater de atendimento podendo ser: Eletivo ou Urgência/Emergência', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (10, '${indicacao_clinica}', 'Indicação de clínica', 'Dados do processo — Texto da indicação clinica', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (11, '${itens_cod_des_qtd_divergencia}', 'Tabela itens — Código, descrição, quantidade e se possui divergência', 'Dados do processo — Tabela com as informações: código, descrição, quantidade e se possui divergência', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (12, '${itens_cod_des_tipo_qtd_divergencia}', 'Tabela itens — Código, descrição, tipo, quantidade e se possui divergência', 'Dados do processo — Tabela com as informações: código, descrição, tipo, quantidade e se possui divergencia', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (13, '${parecer_do_auditor_operadora}', 'Parecer do auditor da operadora', 'Dados do processo — Parecer relatado na abertura do processo pela operadora conforme seu auditor', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (14, '${uso_mat_fab_dist_exclusivo}', 'Uso de material fabricado exclusivo', 'Dados do processo — Pode ser SIM ou NÃO', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (15, '${complemento_uso_mat_fab_dist_exclusivo}', 'Complemento do uso de material fabricado exclusivo', 'Dados do processo — Texto informado quando o uso_mat_fat_dist_exclusivo for igual a SIM', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (16, '${autoriza_material_analogo}', 'Autoriza material analogo', 'Dados do processo — Pode ser SIM ou NÃO', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (17, '${resumo_parecer_desempatador}', 'Resumo parecer desempatador', 'Dados do processo — Conforme regra de realizar parecer cadastrada', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (18, '${desempatadores_nome_conselho_curriculo}', 'Tabela desempatadores', 'Dados do processo — Tabela com as informações: nome, conselho e mini curriculum', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (19, '${nome_beneficiario}', 'Nome do beneficiário', 'Beneficiário — Nome completo', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (20, '${sexo_beneficiário}', 'Sexo do beneficiário', 'Beneficiário — masculino ou feminino', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (21, '${codigo_beneficiário}', 'Código do beneficiário', 'Beneficiário — Número do código do beneficiário completo e sem formatação', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (22, '${nome_profissional_assistente}', 'Nome do profissional assistente', 'Profissional assistente — Nome completo', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (23, '${conselho_profissional_assistente}', 'Conselho do profissional assistente', 'Profissional assistente — Tipo do conselho numero do conselho / UF do conselho . Exemplo: CRM 1234/SC CRO 4321/MG', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (24, '${img_assinatura_desempatador}', 'Imagem da assinatura do desempatador', 'Desempatador — Conforme imagem no cadastro do desempatador', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (25, '${conselho_desempatador}', 'Conselho do desempatador', 'Desempatador — Tipo do conselho numero do conselho / UF do conselho . Exemplo: CRM 1234/SC CRO 4321/MG', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (26, '${nome_desempatador}', 'Nome do desempatador', 'Desempatador — Nome', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (27, '${mini_curriculo}', 'Mini currículo do desempatador', 'Desempatador — Texto do mini currículo', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (28, '${razao_social}', 'Razão social da operadora', 'Operadora (origem) — Razão social', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (29, '${nome_fantasia}', 'Nome fantasia da operadora', 'Operadora (origem) — Nome fantasia', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (30, '${telefone_comercial}', 'Telefone comercial da operadora', 'Operadora (origem) — Telefone comercial', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (31, '${registro_operadora}', 'Registro da operadora', 'Operadora (origem) — Registro', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (32, '${cidade_operadora_origem}', 'Cidade da operadora origem', 'Operadora (origem) — Nome da cidade conforme o endereço da operadora origem', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (33, '${nome_auditor_operadora}', 'Nome do auditor da operadora', 'Auditor Operadora — Nome', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (34, '${conselho_auditor_operadora}', 'Conselho do auditor da operadora', 'Auditor Operadora — Tipo do conselho numero do conselho / UF do conselho . Exemplo: CRM 1234/SC CRO 4321/MG', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (35, '${nome_consultor}', 'Nome do consultor', 'Consultor — Nome', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (36, '${conselho_consultor}', 'Conselho do consultor', 'Consultor — Tipo do conselho numero do conselho / UF do conselho . Exemplo: CRM 1234/SC CRO 4321/MG', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (37, '${nome_auditor_qualidade}', 'Nome do auditor de qualidade', 'Auditor qualidade — Nome', sysdate, 1, null, null);

insert into variavel (id, chave, nome, descricao, created_at, created_by, updated_at, updated_by)
values (38, '${numero_protocolo}', 'Nº Protocolo', 'Número do protocolo vinculado ao processo', sysdate, 1, null, null);

