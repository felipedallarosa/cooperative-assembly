declare
  cursor c_tab is
    select *
      from all_tables at
     where at.owner = 'ASSEMBLY_OWNER';
begin
  for reg in c_tab loop
    --
    execute immediate 'grant select, insert, update, delete on '||reg.table_name||' to ASSEMBLY_RUN';
    --
  end loop;
  --
end;
/