create table animalImfo(
  animalID varchar(3) primary key,
  animalName varchar(20),
  animalType varchar(10),
  gender varchar(3),
  age integer,
  img varchar(40),--内容是图像的地址 默认在java工程的目录下
  imgData blob,
  shelterID integer
);
insert into animalImfo values('001','可乐','狗','公',1,'src/pic/kele.jpg','',1);
insert into animalImfo values('002','土豆','猫','母',1,'src/pic/tudou.jpg','',1);
insert into animalImfo values('003','乐乐','兔子','母',2,'src/pic/lele.jpg','',1);
insert into animalImfo values('004','果冻','狗','公',1,'src/pic/guodong.jpg','',1);
insert into animalImfo values('005','威风','狗','母',3,'src/pic/weifeng.jpg','',2);
insert into animalImfo values('006','小Q','仓鼠','母',2,'src/pic/xiaoq.jpg','',2);
insert into animalImfo values('007','奶茶','猫','公',1,'src/pic/naicha.jpg','',2);
insert into animalImfo values('008','当当','狗','母',3,'src/pic/dangdang.jpg','',2);
insert into animalImfo values('009','闪电','鹦鹉','母',4,'src/pic/shandian.jpg','',3);
insert into animalImfo values('010','维恩','狗','公',2,'src/pic/weien.jpg','',3);
insert into animalImfo values('011','劲宝','狗','公',2,'src/pic/jinbao.jpg','',3);
insert into animalImfo values('012','牛奶','可达鸭','母',1,'src/pic/niunai.jpg','',3);
