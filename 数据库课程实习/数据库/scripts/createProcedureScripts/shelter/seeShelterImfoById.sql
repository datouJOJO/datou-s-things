create or replace PROCEDURE seeShelterimfo(
  NameOrId in varchar,
  imfo out PACKAGE1.my_cursor
)is
begin
open imfo
for select shelterid,sheltername,shelteradr,shelterPost,numOfRoom,leftRoom
from SHELTERIMFO
where SHELTERID = cast(NameOrId as char) or sheltername = NameOrId;
end;

/
