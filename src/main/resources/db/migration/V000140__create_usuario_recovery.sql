CREATE TABLE usuario_recovery (
    uuid                   VARCHAR2(255),
    usuario_id             NUMBER(18)
);

ALTER TABLE usuario_recovery
    ADD CONSTRAINT usuario_recovery_fk
        FOREIGN KEY (usuario_id) REFERENCES usuario (id);
