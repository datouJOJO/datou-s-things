create or replace PROCEDURE UpdateUser(
  inPutId in varchar,
  intex in integer,
  inputData in varchar
)is 
begin 
if intex=0 then
--用户名
  update userImfo set USERNAME = inputData where userid = inPutId;

elsif intex=1 then
--邮箱
  update userImfo set email = inputData where userid = inPutId;

elsif intex=2 then
--电话
  update userImfo set tel = cast(inputData as integer) where userid = inPutId;

elsif intex=3 then
--庇护所
  update userImfo set shelterId = cast(inputData as integer) where userid = inPutId;
  end if;
end;

/
