create or replace procedure seeAllHealth(
  allImfo out PACKAGE1.my_cursor
)is
begin
  open allImfo 
  for select HEALTHIMFOID,ANIMALIMFO.ANIMALNAME,USERIMFO.USERNAME,healthImfo.HEALTHIMFO,healthImfo.CHECKDATE,healthImfo.MOER
  from healthImfo,ANIMALIMFO,USERIMFo
  where healthImfo.ANIMALID = ANIMALIMFO.ANIMALID and USERIMFo.USERID = healthImfo.USERID;
end;

/
