create or replace trigger updateShelter
after update on animalImfo
for each row
declare 
  flag int;  --检测修改的庇护所是否还有剩余
begin 
  select count(*) into flag 
  from SHELTERIMFO
  where SHELTERID = :new.shelterID;
  --如果目标的房间还有剩余
  --且庇护所有变动s
  if (flag>0 and :new.shelterID<>:old.shelterID)then
    update shelterImfo
    set LEFTROOM = leftRoom -1
    where shelterID = :new.shelterID;
    
    --然后原来的房间加1
    update shelterImfo
    set LEFTROOM = leftRoom + 1
    where shelterID = :old.shelterID;
  end if;
end;
/