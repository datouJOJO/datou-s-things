create or replace PROCEDURE judgeAnimal(
  nameOrId in varchar,
  flag out int
)as
begin
  select count(*) into flag 
  from ANIMALIMFO
  where ANIMALID = nameOrId or ANIMALNAME = nameOrId;
end;

/
