package project.app.vo;

import java.io.Serializable;

public class Lecture implements Serializable {
    private static final long serialVersionUID = 1L;

    private int lectNo;
    private Subject subject;
    private String lectName;
    private int room;

    public Lecture() {
    }

    public Lecture(int lectNo) {
        this.lectNo = lectNo;
    }

    public int getLectNo() {
        return lectNo;
    }

    public void setLectNo(int lectNo) {
        this.lectNo = lectNo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getLectName() {
        return lectName;
    }

    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}