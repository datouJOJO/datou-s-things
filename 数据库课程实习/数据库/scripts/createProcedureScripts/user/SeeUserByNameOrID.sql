create or replace procedure SeeUserbyNameOrId(
  --参数
  NameOrID in varchar,
  rs out PACKAGE1.my_cursor
)is
begin
  --打开游标
  open rs for select userid,username,EMAIL,TEL,shelterName
  from userImfo,shelterImfo
  where (userid = NameOrID or username = NameOrID) and 
  userImfo.shelterID = shelterImfo.shelterID;
  
end;

/
