alter table processo add numero_pedido_guia_nova varchar2(30);

update processo set numero_pedido_guia_nova = numero_pedido_guia;

alter table processo drop column numero_pedido_guia;

alter table processo rename column numero_pedido_guia_nova to numero_pedido_guia;
