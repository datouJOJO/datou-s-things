create or replace procedure addImage(
  aID in varchar,
  picData in blob
)is 
begin
  update animalImfo
  set IMGDATA = picData
  where ANIMALID = aID;
end;

/
