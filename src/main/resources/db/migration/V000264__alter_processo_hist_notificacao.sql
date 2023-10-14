ALTER TABLE PROCESSO_HIST_NOTIFICACAO add email_message_id varchar2(100);

ALTER TABLE PROCESSO_HIST_NOTIFICACAO add email_resposta_recebida clob;

ALTER TABLE PROCESSO_HIST_NOTIFICACAO add data_email_resposta_recebida DATE;

ALTER TABLE PROCESSO_HIST_NOTIFICACAO add email_resposta_encaminhado NUMBER(1) DEFAULT 0;

ALTER TABLE PROCESSO_HIST_NOTIFICACAO add data_email_resposta_encaminhado DATE;
