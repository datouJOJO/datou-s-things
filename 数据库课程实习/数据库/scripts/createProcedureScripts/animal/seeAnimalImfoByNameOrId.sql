create or replace PROCEDURE seeAnimalByNameOrId(
  inNameorId in varchar,
  imfo out PACKAGE1.my_cursor
)is
begin
open imfo for select animalid,animalname,animaltype,gender,age,img,imgData,SHELTERIMFO.SHELTERNAME
from ANIMALIMFO,SHELTERIMFO

where (animalid = inNameorId or animalname = inNameorId) 
and ANIMALIMFO.shelterID = SHELTERIMFO.SHELTERID;
end;

/
