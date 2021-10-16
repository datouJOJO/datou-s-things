create or replace procedure addUser(
  uName in varchar,
  upwd in varchar,
  uEmail in varchar,
  uTel in integer,
  uShelterID in integer
)as 
begin
declare numOfUser int;
begin 
  select count(*) into numOfUser from userImfo;
  insert into userImfo values('100'+cast((numOfUser+1) AS VARCHAR(3)),uName,Upwd,uEmail,uTel,uShelterID);
  end;
end;

/

