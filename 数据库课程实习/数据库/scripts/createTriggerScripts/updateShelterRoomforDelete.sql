create or replace trigger deleteAnimal
after delete on animalImfo
for each row
begin
  --ɾ������ʱ
  --��Ӧ�ķ�������1
  update shelterImfo
  set leftRoom = leftRoom + 1
  where :old.shelterID = shelterImfo.shelterID;
end;
/