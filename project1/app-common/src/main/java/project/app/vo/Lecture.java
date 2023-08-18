package project.app.vo;

import java.io.Serializable;

public class Lecture implements Serializable {
    private static final long serialVersionUID = 1L;

    private int no;
    private int subjectNo;
    private int room;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}