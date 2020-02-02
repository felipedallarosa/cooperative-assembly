declare
  cursor c_tab is
    select *
      from all_tables at
     where at.owner = 'ASSEMBLY_OWNER';
begin
  for reg in c_tab loop
    --
    execute immediate 'revoke all on '||reg.table_name||' from ASSEMBLY_RUN';
    --
  end loop;
  --
end;
/