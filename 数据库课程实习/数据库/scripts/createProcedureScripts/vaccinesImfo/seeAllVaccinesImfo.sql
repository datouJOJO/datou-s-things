create or replace PROCEDURE seeAllV(
  allImfo out PACKAGE1.my_cursor
)is
begin
open allImfo 
  for select VACCINESID,ANIMALIMFO.ANIMALNAME,USERIMFO.USERNAME,vaccinesImfo.VACCINESNAME,vaccinesImfo.VACCINATIONDAY,vaccinesImfo.MORE
  from vaccinesImfo,ANIMALIMFO,USERIMFO
  where vaccinesImfo.animalID = ANIMALIMFO.animalID and vaccinesImfo.userID = USERIMFO.userID;
end;

/
