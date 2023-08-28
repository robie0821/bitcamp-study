insert into department(name) values('A학과');
insert into department(name) values('B학과');
insert into department(name) values('C학과');
insert into department(name) values('D학과');

insert into member(dept_no, type, name, email, password)
  values(1, 1, 'stud', 'stud@test.com', sha1('1111'));
insert into member(dept_no, type, name, email, password)
  values(1, 2, 'prof', 'prof@test.com', sha1('1111'));

insert into subject(name, type)
  values('전공A', 1);
insert into subject(name, type)
  values('전공B', 1);
insert into subject(name, type)
  values('교양A', 2);
insert into subject(name, type)
  values('교양B', 2);

insert into lecture(subj_no, name, room)
  values(1, '전공A 오전', 101);
insert into lecture(subj_no, name, room)
  values(1, '전공A 오후', 101);
insert into lecture(subj_no, name, room)
  values(2, '전공B 오전', 102);
insert into lecture(subj_no, name, room)
  values(2, '전공B 오후', 102);
insert into lecture(subj_no, name, room)
  values(3, '교양A 오전', 201);
insert into lecture(subj_no, name, room)
  values(3, '교양A 오후', 201);
insert into lecture(subj_no, name, room)
  values(4, '교양B 오전', 202);
insert into lecture(subj_no, name, room)
  values(4, '교양B 오후', 202);
