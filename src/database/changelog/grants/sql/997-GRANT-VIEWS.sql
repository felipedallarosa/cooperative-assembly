declare
  --
  cursor c_view is
    select aw.view_name
      from all_views aw
     where aw.owner = 'ASSEMBLY_OWNER';
  --
begin
  --
  for reg in c_view loop
    --
    execute immediate 'grant select on '||reg.view_name||' to ASSEMBLY_RUN';
    --
  end loop;
  --
end;
/