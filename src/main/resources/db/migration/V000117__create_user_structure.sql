CREATE TABLE failed_login (
                              id NUMBER(18) NOT NULL,
                              description varchar2(255) NULL,
                              login_request_date DATE NULL,
                              usuario_id NUMBER(18) NULL,
                              username varchar2(255) NULL,
                              usuario_login varchar2(255) NULL
);

ALTER TABLE failed_login
    ADD CONSTRAINT failed_login_pk
        PRIMARY KEY (id);

CREATE SEQUENCE s_failed_login
    MINVALUE 1 MAXVALUE 999999999999999999
    NOCACHE ORDER;


CREATE TABLE token_historico (
                          id NUMBER(18) NOT NULL,
                          token_uuid varchar(255) NULL,
                          token_request_date date NULL,
                          expiration NUMBER(18) NOT NULL,
                          usuario_login VARCHAR2(255) NULL
);

ALTER TABLE token_historico
    ADD CONSTRAINT token_historico_pk
        PRIMARY KEY (id);

CREATE SEQUENCE s_token_historico
    MINVALUE 1 MAXVALUE 999999999999999999
    NOCACHE ORDER;


CREATE TABLE usuario_reset_token (
                                  id NUMBER(18) NOT NULL,
                                  usuario_id NUMBER(18) NOT NULL,
                                  token varchar2(8000) NOT NULL,
                                  status varchar2(255) NOT NULL,
                                  expiration_date TIMESTAMP,
                                  created_at        DATE,
                                  created_by        NUMBER(18),
                                  updated_at        DATE,
                                  updated_by        NUMBER(18)
);

ALTER TABLE usuario_reset_token
    ADD CONSTRAINT usuario_reset_token_pk
        PRIMARY KEY (id);

CREATE SEQUENCE s_usuario_reset_token
    MINVALUE 1 MAXVALUE 999999999999999999
    NOCACHE ORDER;


CREATE TABLE usuario (
                        id NUMBER(18) NOT NULL,
                        active NUMBER(1) NOT NULL,
                        email varchar2(255) NULL,
                        name varchar2(255) NULL,
                        status varchar2(255) NOT NULL,
                        username varchar2(255) NULL,
                        password varchar2(255) NULL,
                        expired_password NUMBER(1) NOT NULL,
                        password_expiration_date date NULL,
                        password_registration_date date NULL
);

ALTER TABLE usuario
    ADD CONSTRAINT usuario_pk
        PRIMARY KEY (id);

ALTER TABLE usuario
    ADD CONSTRAINT usuario_email_uk
        UNIQUE (email);

ALTER TABLE usuario_reset_token
    ADD CONSTRAINT usuario_reset_token_usuario_id_fk
        FOREIGN KEY (usuario_id) REFERENCES usuario (id);

CREATE SEQUENCE s_usuario
    MINVALUE 2 MAXVALUE 999999999999999999
    NOCACHE ORDER;

CREATE TABLE token (
                       id NUMBER(18) NOT NULL,
                       active NUMBER(1) NOT NULL,
                       expiration_date DATE NULL,
                       token_value varchar2(8000) NULL
);

ALTER TABLE token
    ADD CONSTRAINT token_pk
        PRIMARY KEY (id);

CREATE SEQUENCE s_token
    MINVALUE 1 MAXVALUE 999999999999999999
    NOCACHE ORDER;
