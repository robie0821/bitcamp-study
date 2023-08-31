package project.app.vo;

import java.io.Serializable;

public class StudLect implements Serializable {
    private static final long serialVersionUID = 1L;

    private Member student;
    private Lecture lecture;
    private double grade;
    private int rate;
    private String content;

    public Member getStudent() {
        return student;
    }

    public void setStudent(Member student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}