insert into department(dept_no,dept_name) values(1, 'A학과');
insert into department(dept_no,dept_name) values(2, 'B학과');
insert into department(dept_no,dept_name) values(3, 'C학과');
insert into department(dept_no,dept_name) values(4, 'D학과');

insert into member(member_no, dept_no, type, name, email, password)
  values(1000, 1, 0, 'root', 'root@test.com', sha1('1111'));
insert into member(member_no, dept_no, type, name, email, password)
  values(1001, 1, 1, 'stud', 'stud@test.com', sha1('1111'));
insert into member(member_no, dept_no, type, name, email, password)
  values(1002, 1, 2, 'prof', 'prof@test.com', sha1('1111'));

insert into subject(subj_no, subj_name, subj_type)
  values(1, '교양A', 0);
insert into subject(subj_no, subj_name, subj_type)
  values(2, '교양B', 0);
insert into subject(subj_no, subj_name, subj_type)
  values(3, '전공A', 1);
insert into subject(subj_no, subj_name, subj_type)
  values(4, '전공B', 1);

insert into lecture(lect_no, subj_no, lect_name, room)
  values(1, 1, '교양A 오전', 201);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(2, 1, '교양A 오후', 201);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(3, 2, '교양B 오전', 202);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(4, 2, '교양B 오후', 202);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(5, 3, '전공A 오전', 101);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(6, 3, '전공A 오후', 101);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(7, 4, '전공B 오전', 102);
insert into lecture(lect_no, subj_no, lect_name, room)
  values(8, 4, '전공B 오후', 102);
