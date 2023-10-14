ALTER TABLE contrato_fluxo_processo
    ADD CONSTRAINT contrato_fluxo_calendario_fk FOREIGN KEY ( calendario_id )
        REFERENCES calendario ( id );
