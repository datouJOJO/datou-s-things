create table vaccinesImfo(
  vaccinesID varchar(3),
  animalID varchar(3),
  userID varchar(3),
  vaccinesName varchar(30),
  vaccinationDay date,
  more varchar(100)
);

insert into vaccinesImfo values('001','001','001','���Ѱ�',sysdate,'');
insert into vaccinesImfo values('002','002','001','��������',sysdate,'');
insert into vaccinesImfo values('003','003','002','��������',sysdate,'');
insert into vaccinesImfo values('001','004','003','���Ѱ�',sysdate,'');

insert into vaccinesImfo values('001','005','006','���Ѱ�',sysdate,'');
insert into vaccinesImfo values('','006','','',sysdate,'');
insert into vaccinesImfo values('002','007','009','��������',sysdate,'');
insert into vaccinesImfo values('001','008','007','���Ѱ�',sysdate,'');

insert into vaccinesImfo values('004','009','011','�³�������',sysdate,'');
insert into vaccinesImfo values('001','010','014','���Ѱ�',sysdate,'');
insert into vaccinesImfo values('001','011','012','���Ѱ�',sysdate,'');
insert into vaccinesImfo values('005','012','010','Ѽ���ײ�������',sysdate,'');