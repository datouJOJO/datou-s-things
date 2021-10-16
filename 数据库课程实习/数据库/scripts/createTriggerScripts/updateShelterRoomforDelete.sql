create or replace trigger deleteAnimal
after delete on animalImfo
for each row
begin
  --删除动物时
  --相应的房间数加1
  update shelterImfo
  set leftRoom = leftRoom + 1
  where :old.shelterID = shelterImfo.shelterID;
end;
/