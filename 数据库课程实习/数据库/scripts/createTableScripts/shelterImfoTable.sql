create table shelterImfo(
  shelterID integer primary key,
  shelterName varchar(20),
  shelterAdr varchar(30),
  shelterPost integer,
  numOfRoom integer,
  leftRoom integer
);

insert into shelterImfo values(1,'阳光庇护所','阳光路388号',533900,20,16);
insert into shelterImfo values(2,'爱宠之家','祈福路388号',533900,15,11);
insert into shelterImfo values(3,'LoveAndPeace','鲁磨路388号',533900,15,11);