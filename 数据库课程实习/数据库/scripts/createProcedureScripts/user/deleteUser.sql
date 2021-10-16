create or replace procedure deleteUser(
  inPutID in varchar
)as 
begin 
  delete userimfo where USERID = inPutID;
end;

/
