create or replace procedure addAnimal(
  inName in varchar,
  inType in varchar,
  inGender in varchar,
  inAge in integer,
  inImg in varchar,
  InImgData in blob,
  InSId in varchar
)is
begin 
declare numOfUser int;
  begin
  select count(*) into numOfUser from ANIMALIMFO;
  insert into ANIMALIMFO values('20'+cast((numOfUser+1) AS VARCHAR(3)),inName,inType,
  inGender,cast(inAge as integer),inImg,InImgData,cast(InSId as integer));
  end;
end;

/
