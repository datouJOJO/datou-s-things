create or replace PROCEDURE updateAnimmal(
  InId in varchar,
  inData in varchar,
  inIndex in integer
)is
begin
  if inIndex=0 then
    --name
    update ANIMALIMFO set animalName = inData
    where animalId = InId;
    
  elsif inIndex= 1 then
    --shelterid
    update animalImfo set shelterId = cast(inData as integer)
    where animalId = InId;
    
    end if;
end;

/
