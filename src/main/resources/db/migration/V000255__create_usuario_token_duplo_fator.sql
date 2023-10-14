create table USUARIO_TOKEN_DUPLO_FATOR (
    id                                  number(18)    not null,
    usuario_id   						number(18)    not null,
	token								VARCHAR2(6)	  not null,
	uuid								VARCHAR2(255) not null,
	data_expiracao						date		  not null,
    created_at                          date,
    created_by                          number(18),
    updated_at                          date,
    updated_by                          number(18)
);

alter table USUARIO_TOKEN_DUPLO_FATOR
    add constraint usuario_token_duplo_fator_pk
        primary key (id);

alter table USUARIO_TOKEN_DUPLO_FATOR
    add constraint usuario_token_duplo_fator_usuario_fk
        foreign key (usuario_id) references usuario (id);

alter table USUARIO_TOKEN_DUPLO_FATOR
    add constraint usuario_token_duplo_fator_created_by_fk
        foreign key (created_by) references usuario (id);

alter table USUARIO_TOKEN_DUPLO_FATOR
    add constraint usuario_token_duplo_fator_updated_by_fk
        foreign key (updated_by) references usuario (id);

create sequence s_usuario_token_duplo_fator
    minvalue 1 maxvalue 999999999999999999
    nocache order;
