create or replace procedure addShelter(
  Sname in varchar,
  SAdrr in varchar,
  SPost in varchar,
  SRoom in varchar
)is 
begin
declare 
  numId int;
begin
  select count(*) into numId from shelterImfo;
  insert into ShelterImfo values(numId+1,Sname,SAdrr,cast(SPost as int),cast(SRoom as int),cast(SRoom as int));
end;
end;

/
