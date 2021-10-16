create or replace procedure addHealth(
  aID in varchar,
  userID in varchar,
  hImfo in varchar,
  more in varchar
)is
begin
declare 
  hID int;
  begin
  select count(*) into hID from healthImfo;
  insert into HEALTHIMFO values(hID+1,aID,userID,hImfo,sysdate,more);
  end;
end;

/
