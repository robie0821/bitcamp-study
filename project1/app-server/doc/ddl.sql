create table student(
  student_no int not null,
  name varchar(20) not null,
  email varchar(20) not null,
  password varchar(100) null
);
  
create table score(
  score_no int not null,
  student_id int not null,
  sub1 varchar(2) not null,
  sub2 varchar(2) not null,
  sub3 varchar(2) not null,
  sub4 varchar(2) not null,
  grade float not null,
  scholar boolean not null
);
  
create table review(
  review_no int not null,
  student_id int not null,
  subject_id int not null,
  rate int not null,
  content text null
);

-- Primary Key 설정
alter table student
  add constraint primary key(student_no),
  modify column student_no int not null auto_increment;

alter table student
  auto_increment=1001;
  
alter table score
  add constraint primary key(score_no),
  modify column score_no int not null auto_increment;
  
  alter table score
  auto_increment=11;

alter table review
  add constraint primary key(review_no),
  modify column review_no int not null auto_increment;
  
  alter table review
  auto_increment=101;

-- Unique 설정
alter table student
  add constraint student_uk unique (email);
  
alter table score
  add constraint score_uk unique (student_id);
  
alter table review
  add constraint review_uk unique (student_id, subject_id);

-- Foreign Key 설정
alter table score
  add constraint score_fk foreign key (student_id) references student (student_no);

alter table review
  add constraint review_fk foreign key (student_id) references student (student_no);