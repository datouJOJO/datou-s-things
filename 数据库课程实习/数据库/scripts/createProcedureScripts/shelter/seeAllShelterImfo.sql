create or replace PROCEDURE seeAllShelter(
  imfo out PACKAGE1.my_cursor
)is
begin
open imfo
for select shelterid,sheltername,shelteradr,shelterPost,numOfRoom,leftRoom
from SHELTERIMFO;
end;

/
