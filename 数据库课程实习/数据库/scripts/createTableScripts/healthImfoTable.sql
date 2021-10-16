create table healthImfo(
  healthImfoID varchar(3) primary key,
  animalID varchar(3),
  userID varchar(3),
  healthImfo varchar(20),
  checkDate date,
  moer varchar(100)
);

insert into healthImfo values('001','001','001','½¡¿µ',sysdate,'');
insert into healthImfo values('002','002','001','½¡¿µ',sysdate,'');
insert into healthImfo values('003','003','002','½¡¿µ',sysdate,'');
insert into healthImfo values('004','004','003','½¡¿µ',sysdate,'');

insert into healthImfo values('005','005','006','½¡¿µ',sysdate,'');
insert into healthImfo values('006','006','009','½¡¿µ',sysdate,'');
insert into healthImfo values('007','007','006','½¡¿µ',sysdate,'');
insert into healthImfo values('008','008','007','½¡¿µ',sysdate,'');

insert into healthImfo values('009','009','011','½¡¿µ',sysdate,'');
insert into healthImfo values('010','010','014','½¡¿µ',sysdate,'');
insert into healthImfo values('011','011','012','½¡¿µ',sysdate,'');
insert into healthImfo values('012','012','010','½¡¿µ',sysdate,'');