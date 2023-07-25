create table student(
  student_no int not null,
  name varchar(20) not null,
  email varchar(20) not null,
  password varchar(100) null,
  score boolean default 0
);
  
create table score(
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
  modify column student_no int not null auto_increment = 1001;
  
alter table score
  add constraint primary key(student_no);

alter table review
  add constraint primary key(review_no),
  modify column review_no int not null auto_increment;

-- Unique 설정
alter table student
  add constraint student_uk unique (email);
  
alter table review
  add constraint review_uk unique (student_id, subject_id);

-- Foreign Key 설정
alter table score
  add constraint score_fk foreign key (student_id) references student (student_no);

  alter table review
  add constraint review_fk foreign key (student_id) references student (student_no);
  