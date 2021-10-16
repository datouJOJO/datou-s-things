create or replace trigger insertAnimal
after insert on animalimfo
for each row
declare 
  roomNum int;
begin
  --插入数据时
  --根据插入的庇护所的id来更新庇护所的庇护所所生房间的数量
  --首先判断是否还存在房间
  select leftRoom into roomNum 
  from shelterImfo 
  where :new.shelterid = SHELTERID;
  if roomNum>0 then
    update shelterImfo set leftroom = leftroom -1
    where SHELTERID = :new.shelterid;
  end if;
end;
/