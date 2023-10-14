CREATE TABLE AUDITOR_ESPECIALIDADE (
	ID NUMBER(18) NOT NULL,
	AUDITOR_ID NUMBER(18) NOT NULL,
	ESPECIALIDADE_ID NUMBER(18) NOT NULL,
	CREATED_AT DATE,
	CREATED_BY NUMBER(18),
	UPDATED_AT DATE,
	UPDATED_BY NUMBER(18)
);

ALTER TABLE AUDITOR_ESPECIALIDADE
    ADD CONSTRAINT AUDITOR_ESPECIALIDADE_PK
        PRIMARY KEY (ID);

ALTER TABLE AUDITOR_ESPECIALIDADE
	ADD CONSTRAINT AUDITOR_ESPECIALIDADE_UK
		UNIQUE (AUDITOR_ID, ESPECIALIDADE_ID);

ALTER TABLE AUDITOR_ESPECIALIDADE
    ADD CONSTRAINT AUDITOR_ESPECIALIDADE_AUDITOR_FK
        FOREIGN KEY (AUDITOR_ID) REFERENCES AUDITOR (ID);

ALTER TABLE AUDITOR_ESPECIALIDADE
    ADD CONSTRAINT AUDITOR_ESPECIALIDADE_ESPECIALIDADE_FK
        FOREIGN KEY (ESPECIALIDADE_ID) REFERENCES ESPECIALIDADE (ID);

ALTER TABLE AUDITOR_ESPECIALIDADE
	ADD CONSTRAINT AUDITOR_ESPECIALIDADE_CREATED_BY_FK
		FOREIGN KEY (CREATED_BY) REFERENCES USUARIO (ID);

ALTER TABLE AUDITOR_ESPECIALIDADE
	ADD CONSTRAINT AUDITOR_ESPECIALIDADE_UPDATED_BY_FK
		FOREIGN KEY (UPDATED_BY) REFERENCES USUARIO (ID);

CREATE SEQUENCE S_AUDITOR_ESPECIALIDADE MINVALUE 1 MAXVALUE 999999999999999999 START WITH 1 INCREMENT BY 1 NOCACHE ORDER;