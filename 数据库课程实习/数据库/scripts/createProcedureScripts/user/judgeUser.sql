create or replace PROCEDURE judgeUser (
  idOrName in varchar,
  flag out int
) AUTHID DEFINER IS
BEGIN
    select count(*) into flag
    from userImfo
    where userid = idOrName or username = idOrName;
END;

/
