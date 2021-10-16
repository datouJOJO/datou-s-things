declare
  outid varchar(3);
  outname varchar(20);
  outEmail varchar(20);
  outTel int;
  outShelterID int;
begin
seeuserbyname('´óÍ·',outid,outname,outEmail,outTel,outShelterID);
dbms_output.put_line(outid);
dbms_output.put_line(outname);
dbms_output.put_line(outEmail);
dbms_output.put_line(outTel);
dbms_output.put_line(outShelterID);
end;