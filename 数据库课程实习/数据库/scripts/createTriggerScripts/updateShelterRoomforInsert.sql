create or replace trigger insertAnimal
after insert on animalimfo
for each row
declare 
  roomNum int;
begin
  --��������ʱ
  --���ݲ���ıӻ�����id�����±ӻ����ıӻ����������������
  --�����ж��Ƿ񻹴��ڷ���
  select leftRoom into roomNum 
  from shelterImfo 
  where :new.shelterid = SHELTERID;
  if roomNum>0 then
    update shelterImfo set leftroom = leftroom -1
    where SHELTERID = :new.shelterid;
  end if;
end;
/