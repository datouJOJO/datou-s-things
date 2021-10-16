create or replace procedure seeImage(
  imgData out PACKAGE1.my_cursor
)is 
begin
  open imgData for select ANIMALID,IMG
  from animalImfo;
end;

/
