create or replace procedure addV(
  vID in varchar,
  aID in varchar,
  userID in varchar,
  vImfo in varchar,
  more in varchar
)is
begin
declare 
  begin
  insert into VACCINESIMFO values(vID,aID,userID,vImfo,sysdate,more);
  end;
end;

/
