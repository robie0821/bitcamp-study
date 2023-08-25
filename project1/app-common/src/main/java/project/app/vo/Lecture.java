package project.app.vo;

import java.io.Serializable;

public class Lecture implements Serializable {
    private static final long serialVersionUID = 1L;

    private int lectNo;
    private Subject subject;
    private String name;
    private int room;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}