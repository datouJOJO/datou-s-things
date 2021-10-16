create or replace PROCEDURE seeUserNameAndPwd(
  NameAndPwd out PACKAGE1.my_cursor
)is
begin 
open NameAndPwd for select USERNAME,Pwd from userImfo;
end;

/
