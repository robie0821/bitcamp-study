-- 강의
CREATE TABLE lecture (
	lect_no INTEGER NOT NULL,
	subj_no INTEGER NOT NULL,
	room INTEGER NULL
);

-- 강의
ALTER TABLE lecture
	ADD CONSTRAINT PK_lecture -- 강의 기본키
	PRIMARY KEY (
	lect_no -- 강의번호
	);

ALTER TABLE lecture
	MODIFY COLUMN lect_no INTEGER NOT NULL AUTO_INCREMENT;

-- 멤버
CREATE TABLE member (
	no INTEGER NOT NULL,
	type INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(40) NOT NULL,
	password VARCHAR(50) NOT NULL
);

-- 멤버
ALTER TABLE member
	ADD CONSTRAINT PK_member -- 멤버 기본키
	PRIMARY KEY (
	no -- 멤버번호
	);

-- 멤버 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
	ON member ( -- 멤버
				email ASC -- 이메일
	);

ALTER TABLE member
	MODIFY COLUMN no INTEGER NOT NULL AUTO_INCREMENT;

-- 교수배정
CREATE TABLE prof_lect (
	no INTEGER NOT NULL,
	lect_no INTEGER NOT NULL
);

-- 교수배정
ALTER TABLE prof_lect
	ADD CONSTRAINT PK_prof_lect -- 교수배정 기본키
	PRIMARY KEY (
	no,      -- 멤버번호
	lect_no  -- 강의번호
	);

-- 수강신청
CREATE TABLE stud_lect (
	no INTEGER NOT NULL,
	lect_no INTEGER NOT NULL,
	state BOOLEAN NULL,
	grade DOUBLE NULL,
	rate INTEGER NULL,
	content VARCHAR(255) NULL
);

-- 수강신청
ALTER TABLE stud_lect
	ADD CONSTRAINT PK_stud_lect -- 수강신청 기본키
	PRIMARY KEY (
	no,      -- 멤버번호
	lect_no  -- 강의번호
	);

-- 과목
CREATE TABLE subject (
	subj_no INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL
);

-- 과목
ALTER TABLE subject
	ADD CONSTRAINT PK_subject -- 과목 기본키
	PRIMARY KEY (
	subj_no -- 과목코드
	);

ALTER TABLE subject
	MODIFY COLUMN subj_no INTEGER NOT NULL AUTO_INCREMENT;

-- 강의
ALTER TABLE lecture
	ADD CONSTRAINT FK_subject_TO_lecture -- 과목 -> 강의
	FOREIGN KEY (
	subj_no -- 과목코드
	)
	REFERENCES subject ( -- 과목
	subj_no -- 과목코드
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

-- 교수배정
ALTER TABLE prof_lect
	ADD CONSTRAINT FK_member_TO_prof_lect -- 멤버 -> 교수배정
	FOREIGN KEY (
	no -- 멤버번호
	)
	REFERENCES member ( -- 멤버
	no -- 멤버번호
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

-- 수강신청
ALTER TABLE stud_lect
	ADD CONSTRAINT FK_member_TO_stud_lect -- 멤버 -> 수강신청
	FOREIGN KEY (
	no -- 멤버번호
	)
	REFERENCES member ( -- 멤버
	no -- 멤버번호
	);