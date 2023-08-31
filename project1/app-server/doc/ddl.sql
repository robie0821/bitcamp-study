-- 멤버
DROP TABLE IF EXISTS member RESTRICT;

-- 학과
DROP TABLE IF EXISTS department RESTRICT;

-- 과목
DROP TABLE IF EXISTS subject RESTRICT;

-- 강의
DROP TABLE IF EXISTS lecture RESTRICT;

-- 수강신청
DROP TABLE IF EXISTS stud_lect RESTRICT;

-- 교수배정
DROP TABLE IF EXISTS prof_lect RESTRICT;

-- 멤버
CREATE TABLE member (
	member_no   INTEGER      NOT NULL COMMENT '멤버번호', -- 멤버번호
	dept_no     INTEGER      NOT NULL COMMENT '학과번호', -- 학과번호
	member_type INTEGER      NOT NULL COMMENT '분류', -- 분류
	member_name VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
	email       VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
	password    VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
	photo       VARCHAR(255) NULL     COMMENT '사진' -- 사진
)
COMMENT '멤버';

-- 멤버
ALTER TABLE member
	ADD CONSTRAINT PK_member -- 멤버 기본키
	PRIMARY KEY (
	member_no -- 멤버번호
	);

-- 멤버 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
	ON member ( -- 멤버
		email ASC -- 이메일
	);

ALTER TABLE member
	MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '멤버번호';

ALTER TABLE member
	AUTO_INCREMENT = 1001;

-- 학과
CREATE TABLE department (
	dept_no   INTEGER     NOT NULL COMMENT '학과번호', -- 학과번호
	dept_name VARCHAR(50) NOT NULL COMMENT '이름' -- 이름
)
COMMENT '학과';

-- 학과
ALTER TABLE department
	ADD CONSTRAINT PK_department -- 학과 기본키
	PRIMARY KEY (
	dept_no -- 학과번호
	);

-- 학과 유니크 인덱스
CREATE UNIQUE INDEX UIX_department
	ON department ( -- 학과
		dept_name ASC -- 이름
	);

ALTER TABLE department
	MODIFY COLUMN dept_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '학과번호';

ALTER TABLE department
	AUTO_INCREMENT = 1;

-- 과목
CREATE TABLE subject (
	subj_no   INTEGER     NOT NULL COMMENT '과목코드', -- 과목코드
	subj_name VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	subj_type INTEGER     NOT NULL COMMENT '분류' -- 분류
)
COMMENT '과목';

-- 과목
ALTER TABLE subject
	ADD CONSTRAINT PK_subject -- 과목 기본키
	PRIMARY KEY (
	subj_no -- 과목코드
	);

-- 과목 유니크 인덱스
CREATE UNIQUE INDEX UIX_subject
	ON subject ( -- 과목
		subj_name ASC -- 이름
	);

ALTER TABLE subject
	MODIFY COLUMN subj_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '과목코드';

ALTER TABLE subject
	AUTO_INCREMENT = 1;

-- 강의
CREATE TABLE lecture (
	lect_no   INTEGER     NOT NULL COMMENT '강의번호', -- 강의번호
	subj_no   INTEGER     NOT NULL COMMENT '과목코드', -- 과목코드
	lect_name VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	room      INTEGER     NULL     COMMENT '강의실' -- 강의실
)
COMMENT '강의';

-- 강의
ALTER TABLE lecture
	ADD CONSTRAINT PK_lecture -- 강의 기본키
	PRIMARY KEY (
	lect_no -- 강의번호
	);

-- 강의 유니크 인덱스
CREATE UNIQUE INDEX UIX_lecture
	ON lecture ( -- 강의
		lect_name ASC -- 이름
	);

ALTER TABLE lecture
	MODIFY COLUMN lect_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의번호';

ALTER TABLE lecture
	AUTO_INCREMENT = 1;

-- 수강신청
CREATE TABLE stud_lect (
	member_no INTEGER    NOT NULL COMMENT '멤버번호', -- 멤버번호
	lect_no   INTEGER    NOT NULL COMMENT '강의번호', -- 강의번호
	grade     DOUBLE     NULL     COMMENT '학점', -- 학점
	rate      INTEGER    NULL     COMMENT '평가', -- 평가
	content   MEDIUMTEXT NULL     COMMENT '내용' -- 내용
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE stud_lect
	ADD CONSTRAINT PK_stud_lect -- 수강신청 기본키
	PRIMARY KEY (
	member_no, -- 멤버번호
	lect_no    -- 강의번호
	);

-- 교수배정
CREATE TABLE prof_lect (
	member_no INTEGER NOT NULL COMMENT '멤버번호', -- 멤버번호
	lect_no   INTEGER NOT NULL COMMENT '강의번호' -- 강의번호
)
COMMENT '교수배정';

-- 교수배정
ALTER TABLE prof_lect
	ADD CONSTRAINT PK_prof_lect -- 교수배정 기본키
	PRIMARY KEY (
	member_no, -- 멤버번호
	lect_no    -- 강의번호
	);

-- 멤버
ALTER TABLE member
	ADD CONSTRAINT FK_department_TO_member -- 학과 -> 멤버
	FOREIGN KEY (
	dept_no -- 학과번호
	)
	REFERENCES department ( -- 학과
	dept_no -- 학과번호
	);

-- 강의
ALTER TABLE lecture
	ADD CONSTRAINT FK_subject_TO_lecture -- 과목 -> 강의
	FOREIGN KEY (
	subj_no -- 과목코드
	)
	REFERENCES subject ( -- 과목
	subj_no -- 과목코드
	);

-- 수강신청
ALTER TABLE stud_lect
	ADD CONSTRAINT FK_member_TO_stud_lect -- 멤버 -> 수강신청
	FOREIGN KEY (
	member_no -- 멤버번호
	)
	REFERENCES member ( -- 멤버
	member_no -- 멤버번호
	);

-- 수강신청
ALTER TABLE stud_lect
	ADD CONSTRAINT FK_lecture_TO_stud_lect -- 강의 -> 수강신청
	FOREIGN KEY (
	lect_no -- 강의번호
	)
	REFERENCES lecture ( -- 강의
	lect_no -- 강의번호
	);

-- 교수배정
ALTER TABLE prof_lect
	ADD CONSTRAINT FK_member_TO_prof_lect -- 멤버 -> 교수배정
	FOREIGN KEY (
	member_no -- 멤버번호
	)
	REFERENCES member ( -- 멤버
	member_no -- 멤버번호
	);

-- 교수배정
ALTER TABLE prof_lect
	ADD CONSTRAINT FK_lecture_TO_prof_lect -- 강의 -> 교수배정
	FOREIGN KEY (
	lect_no -- 강의번호
	)
	REFERENCES lecture ( -- 강의
	lect_no -- 강의번호
	);