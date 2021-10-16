create or replace PROCEDURE judgeShelter(
  inID in int,
  flag out int
)as
begin
  select count(*) into flag from SHELTERIMFO
  where SHELTERID = inID;
end;

/

