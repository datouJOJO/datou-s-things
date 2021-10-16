create or replace procedure deleteAnimal(
  inID in varchar
)is 
begin
  delete ANIMALIMFO where ANIMALID = inID;
end;

/
