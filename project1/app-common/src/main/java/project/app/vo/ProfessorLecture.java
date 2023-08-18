package project.app.vo;

import java.io.Serializable;

public class ProfessorLecture implements Serializable {
    private static final long serialVersionUID = 1L;

    private int professorNo;
    private int lectureNo;

    public int getProfessorNo() {
        return professorNo;
    }

    public void setProfessorNo(int professorNo) {
        this.professorNo = professorNo;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }
}
