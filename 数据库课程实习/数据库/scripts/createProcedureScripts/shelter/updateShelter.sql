create or replace procedure updateS(
  inIndex in varchar,
  inDate in varchar,
  inID in varchar
)is
begin
declare 
  upDtype int;
  UsedRoom int;
  AllRoom int;
  begin
    if cast(inIndex as int) = 0 then
      update shelterImfo set ShelterName = inDate
      where shelterID = cast(inID as int);
    elsif cast(inIndex as int) = 1 then
      update shelterImfo set ShelterADR = inDate
      where shelterID = cast(inID as int);
    elsif cast(inIndex as int) = 2 then
      update shelterImfo set ShelterPost = cast(inDate as int)
      where shelterID = cast(inID as int);
    elsif cast(inIndex as int) = 3 then
    --Ê£Óà·¿¼äÊý
      select numOfRoom ,leftRoom into AllRoom,UsedRoom from shelterImfo
      where shelterID = cast(inID as int);
      
      update shelterImfo set numOfRoom = cast(inDate as int)
      where shelterID = cast(inID as int);
      
      update shelterImfo set leftRoom = cast(inDate as int)-(AllRoom-leftRoom)
      where shelterID = cast(inID as int);
    end if;
  end;
end;

/
