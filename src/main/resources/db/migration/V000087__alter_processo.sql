ALTER TABLE processo ADD dm_motivo_reprovar_processo_id NUMBER(10);
ALTER TABLE processo ADD dm_reprovar_motivado_id NUMBER(10);

ALTER TABLE processo
    ADD CONSTRAINT dm_reprovar_motivado_fk FOREIGN KEY ( dm_reprovar_motivado_id )
        REFERENCES dominio ( id );

ALTER TABLE processo
    ADD CONSTRAINT dm_motivo_reprovar_processo_fk FOREIGN KEY ( dm_motivo_reprovar_processo_id )
        REFERENCES dominio ( id );
