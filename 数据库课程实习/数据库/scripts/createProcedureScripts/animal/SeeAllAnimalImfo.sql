create or replace procedure seeAllAnimals(
  allImfo out PACKAGE1.my_cursor
)is
begin
open allImfo for select ANIMALID ,
ANIMALNAME ,
ANIMALTYPE ,
GENDER ,
AGE ,
img ,
imgdata,
SHELTERIMFO.SHELTERNAME
from ANIMALIMFO,SHELTERIMFO
where ANIMALIMFO.SHELTERID = SHELTERIMFO.SHELTERID;
end;

/
