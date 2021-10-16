create or replace PROCEDURE seeManagerNameAndPwd(
  NameAndPwd out PACKAGE1.my_cursor
)is
begin 
open NameAndPwd for select managerName,Pwd from MANAGERIMFO;
end;

/
