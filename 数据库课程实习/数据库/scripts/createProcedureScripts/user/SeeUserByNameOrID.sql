create or replace procedure SeeUserbyNameOrId(
  --����
  NameOrID in varchar,
  rs out PACKAGE1.my_cursor
)is
begin
  --���α�
  open rs for select userid,username,EMAIL,TEL,shelterName
  from userImfo,shelterImfo
  where (userid = NameOrID or username = NameOrID) and 
  userImfo.shelterID = shelterImfo.shelterID;
  
end;

/
