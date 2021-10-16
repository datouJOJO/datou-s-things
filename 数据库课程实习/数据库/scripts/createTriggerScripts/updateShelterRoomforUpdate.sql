create or replace trigger updateShelter
after update on animalImfo
for each row
declare 
  flag int;  --����޸ĵıӻ����Ƿ���ʣ��
begin 
  select count(*) into flag 
  from SHELTERIMFO
  where SHELTERID = :new.shelterID;
  --���Ŀ��ķ��仹��ʣ��
  --�ұӻ����б䶯s
  if (flag>0 and :new.shelterID<>:old.shelterID)then
    update shelterImfo
    set LEFTROOM = leftRoom -1
    where shelterID = :new.shelterID;
    
    --Ȼ��ԭ���ķ����1
    update shelterImfo
    set LEFTROOM = leftRoom + 1
    where shelterID = :old.shelterID;
  end if;
end;
/