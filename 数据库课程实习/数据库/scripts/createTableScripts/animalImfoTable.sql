create table animalImfo(
  animalID varchar(3) primary key,
  animalName varchar(20),
  animalType varchar(10),
  gender varchar(3),
  age integer,
  img varchar(40),--������ͼ��ĵ�ַ Ĭ����java���̵�Ŀ¼��
  imgData blob,
  shelterID integer
);
insert into animalImfo values('001','����','��','��',1,'src/pic/kele.jpg','',1);
insert into animalImfo values('002','����','è','ĸ',1,'src/pic/tudou.jpg','',1);
insert into animalImfo values('003','����','����','ĸ',2,'src/pic/lele.jpg','',1);
insert into animalImfo values('004','����','��','��',1,'src/pic/guodong.jpg','',1);
insert into animalImfo values('005','����','��','ĸ',3,'src/pic/weifeng.jpg','',2);
insert into animalImfo values('006','СQ','����','ĸ',2,'src/pic/xiaoq.jpg','',2);
insert into animalImfo values('007','�̲�','è','��',1,'src/pic/naicha.jpg','',2);
insert into animalImfo values('008','����','��','ĸ',3,'src/pic/dangdang.jpg','',2);
insert into animalImfo values('009','����','����','ĸ',4,'src/pic/shandian.jpg','',3);
insert into animalImfo values('010','ά��','��','��',2,'src/pic/weien.jpg','',3);
insert into animalImfo values('011','����','��','��',2,'src/pic/jinbao.jpg','',3);
insert into animalImfo values('012','ţ��','�ɴ�Ѽ','ĸ',1,'src/pic/niunai.jpg','',3);
