declare
  cursor c_seq is
    select *
      from all_sequences seq
    where seq.sequence_owner = 'ASSEMBLY_OWNER';
begin
  --
  for reg in c_seq loop
    --
    execute immediate 'grant select on '||reg.sequence_name||' to ASSEMBLY_RUN';
    --
  end loop;
  --
end;
/