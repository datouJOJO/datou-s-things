create or replace PROCEDURE seeHealthImfo(
  NameOrId in varchar,
  inIndex in varchar,
  imfo out PACKAGE1.my_cursor
)is
begin
  if cast(inIndex as int) = 0 then
    --����id
    open imfo for select healthImfo.HEALTHIMFOID,ANIMALIMFO.ANIMALNAME,USERIMFO.USERNAME,healthImfo.HEALTHIMFO,healthImfo.CHECKDATE,healthImfo.MOER
    from healthImfo,USERIMFO,ANIMALIMFO
    where (healthImfo.userId = Userimfo.userId and healthImfo.animalId = animalImfo.animalId)
    and healthImfo.animalId = NameOrId;
  elsif cast(inIndex as int) = 1 then
    --Ա��id
    open imfo for select healthImfo.HEALTHIMFOID,ANIMALIMFO.ANIMALNAME,USERIMFO.USERNAME,healthImfo.HEALTHIMFO,healthImfo.CHECKDATE,healthImfo.MOER
    from healthImfo,USERIMFO,ANIMALIMFO
    where (healthImfo.userId = Userimfo.userId and healthImfo.animalId = animalImfo.animalId)
    and healthImfo.userID = NameOrId;
  end if;
end;

/
