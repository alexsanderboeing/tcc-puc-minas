CREATE TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE (
	ID NUMBER(18) NOT NULL,
	PROFISSIONAL_ASSISTENTE_ID NUMBER(18) NOT NULL,
	ESPECIALIDADE_ID NUMBER(18) NOT NULL,
	CREATED_AT DATE,
	CREATED_BY NUMBER(18),
	UPDATED_AT DATE,
	UPDATED_BY NUMBER(18)
);

ALTER TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE
    ADD CONSTRAINT PROFISSIONAL_ASSIST_ESPECIALIDADE_PK
        PRIMARY KEY (ID);

ALTER TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE
	ADD CONSTRAINT PROFISSIONAL_ASSIST_ESPECIALIDADE_UK
		UNIQUE (PROFISSIONAL_ASSISTENTE_ID, ESPECIALIDADE_ID);

ALTER TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE
    ADD CONSTRAINT PROFISSIONAL_ASSIST_ESPECIALIDADE_PROFISSIONAL_ASSIST_FK
        FOREIGN KEY (PROFISSIONAL_ASSISTENTE_ID) REFERENCES PROFISSIONAL_ASSISTENTE (ID);

ALTER TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE
    ADD CONSTRAINT PROFISSIONAL_ASSIST_ESPECIALIDADE_ESPECIALIDADE_FK
        FOREIGN KEY (ESPECIALIDADE_ID) REFERENCES ESPECIALIDADE (ID);

ALTER TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE
	ADD CONSTRAINT PROFISSIONAL_ASSIST_ESPECIALIDADE_CREATED_BY_FK
		FOREIGN KEY (CREATED_BY) REFERENCES USUARIO (ID);

ALTER TABLE PROFISSIONAL_ASSIST_ESPECIALIDADE
	ADD CONSTRAINT PROFISSIONAL_ASSIST_ESPECIALIDADE_UPDATED_BY_FK
		FOREIGN KEY (UPDATED_BY) REFERENCES USUARIO (ID);

CREATE SEQUENCE S_PROFISSIONAL_ASSIST_ESPECIALIDADE MINVALUE 1 MAXVALUE 999999999999999999 START WITH 1 INCREMENT BY 1 NOCACHE ORDER;
