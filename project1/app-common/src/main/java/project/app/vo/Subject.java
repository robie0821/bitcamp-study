package project.app.vo;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private int subjNo;
    private String subjName;
    private int subjType;

    public Subject() {
    }

    public Subject(int subjNo) {
        this.subjNo = subjNo;
    }

    public int getSubjNo() {
        return subjNo;
    }

    public void setSubjNo(int subjNo) {
        this.subjNo = subjNo;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public int getSubjType() {
        return subjType;
    }

    public void setSubjType(int subjType) {
        this.subjType = subjType;
    }
}