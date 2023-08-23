package project.app.vo;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private int subjectNo;
    private int name;

    public int getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}