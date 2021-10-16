create or replace PROCEDURE seeAllUserImfo(
  allImfo out PACKAGE1.my_cursor
)is
begin
  open allImfo for select userid,userName,EMAIL,USERIMFO.TEL,SHELTERIMFO.SHELTERNAME from USERIMFO,SHELTERIMFO
  where USERIMFO.SHELTERID=SHELTERIMFO.SHELTERID;
end;

/
