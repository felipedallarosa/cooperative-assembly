declare
  cursor c_seq is
    select *
      from all_sequences seq
    where seq.sequence_owner = 'ASSEMBLY_OWNER';
begin
  --
  for reg in c_seq loop
    --
    execute immediate 'revoke all on '||reg.sequence_name||' from ASSEMBLY_RUN';
    --
  end loop;
  --
end;
/