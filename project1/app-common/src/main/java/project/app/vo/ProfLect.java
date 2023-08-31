package project.app.vo;

import java.io.Serializable;

public class ProfLect implements Serializable {
    private static final long serialVersionUID = 1L;

    private Member professor;
    private Lecture lecture;

    public Member getProfessor() {
        return professor;
    }

    public void setProfessor(Member professor) {
        this.professor = professor;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}