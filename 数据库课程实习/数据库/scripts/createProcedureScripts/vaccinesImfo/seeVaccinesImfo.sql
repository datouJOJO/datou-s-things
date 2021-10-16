create or replace PROCEDURE seeVaccinesImfo(
  NameOrId in varchar,
  inIndex in varchar,
  imfo out PACKAGE1.my_cursor
)is
begin
  if cast(inIndex as int) = 0 then
    --动物id
    open imfo for select vaccinesid,animalName,userName,vaccinesName,vaccinationDay,more
    from VACCINESIMFO,USERIMFO,ANIMALIMFO
    where (VACCINESIMFO.userId = Userimfo.userId and VACCINESIMFO.animalId = animalImfo.animalId)
    and VACCINESIMFO.animalId = NameOrId;
  elsif cast(inIndex as int) = 1 then
    --员工id
    open imfo for select vaccinesid,animalName,userName,vaccinesName,vaccinationDay,more
    from VACCINESIMFO,USERIMFO,ANIMALIMFO
    where (VACCINESIMFO.userId = Userimfo.userId and VACCINESIMFO.animalId = animalImfo.animalId)
    and VACCINESIMFO.animalId = NameOrId;
  end if;
end;

/
