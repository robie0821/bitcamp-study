package project.app.vo;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private int no;
    private int name;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}