set serveroutput on
commit;

declare 
  outid varchar(3);
  outname varchar(20);
  outtype varchar(10);
  outgender varchar(3);
  outage int;
  outimage varchar(100);
  outshelter varchar(100);
begin 
  seeAnimalByNameOrId('ÍÁ¶¹',outid,outname,outtype,outgender,outage,outimage,outshelter);
  dbms_output.put_line(outid);
  dbms_output.put_line(outname);
  dbms_output.put_line(outtype);
  dbms_output.put_line(outgender);
  dbms_output.put_line(outage);
  dbms_output.put_line(outimage);
  dbms_output.put_line(outshelter);
end;