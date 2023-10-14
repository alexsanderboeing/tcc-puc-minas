alter table processo add dm_cobrar_processo_id NUMBER(10);

update processo
set dm_cobrar_processo_id = case when cobrar_processo = 0 then 74 else 73 end
where
    cobrar_processo is not null;
