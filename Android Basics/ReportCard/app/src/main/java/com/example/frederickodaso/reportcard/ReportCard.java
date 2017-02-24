package com.example.frederickodaso.reportcard;

import java.util.HashMap;

/**
 * Created by frederickodaso on 1/28/17.
 */
public class ReportCard {

    private int mGrade1;
    private int mGrade2;
    private int mGrade3;
    private int mGrade4;
    private int mGrade5;
    private String mClassName1;
    private String mClassName2;
    private String mClassName3;
    private String mClassName4;
    private String mClassName5;
    private HashMap<String, Integer> mGradebook;

    public ReportCard(int grade1, int grade2, int grade3, int grade4, int grade5,
                      String className1, String className2, String className3, String className4, String className5) {
        mGrade1 = grade1;
        mGrade2 = grade2;
        mGrade3 = grade3;
        mGrade4 = grade4;
        mGrade5 = grade5;
        mClassName1 = className1;
        mClassName2 = className2;
        mClassName3 = className3;
        mClassName4 = className4;
        mClassName5 = className5;
    }

    public ReportCard(HashMap<String, Integer> gradebook) {
        mGradebook = gradebook;
    }

    public int getGrade1() {
        return mGrade1;
    }

    public void setGrade1(int grade1) {
        mGrade1 = grade1;
    }

    public int getGrade2() {
        return mGrade2;
    }

    public void setGrade2(int grade2) {
        mGrade2 = grade2;
    }

    public int getGrade3() {
        return mGrade3;
    }

    public void setGrade3(int grade3) {
        mGrade3 = grade3;
    }

    public int getGrade4() {
        return mGrade4;
    }

    public void setGrade4(int grade4) {
        mGrade4 = grade4;
    }

    public int getGrade5() {
        return mGrade5;
    }

    public void setGrade5(int grade5) {
        mGrade5 = grade5;
    }

    public String getClassName1() {
        return mClassName1;
    }

    public void setClassName1(String className1) {
        mClassName1 = className1;
    }

    public String getClassName2() {
        return mClassName2;
    }

    public void setClassName2(String className2) {
        mClassName2 = className2;
    }

    public String getClassName3() {
        return mClassName3;
    }

    public void setClassName3(String className3) {
        mClassName3 = className3;
    }

    public String getClassName4() {
        return mClassName4;
    }

    public void setClassName4(String className4) {
        mClassName4 = className4;
    }

    public String getClassName5() {
        return mClassName5;
    }

    public void setClassName5(String className5) {
        mClassName5 = className5;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "mGrade1=" + mGrade1 +
                ", mGrade2=" + mGrade2 +
                ", mGrade3=" + mGrade3 +
                ", mGrade4=" + mGrade4 +
                ", mGrade5=" + mGrade5 +
                ", mClassName1='" + mClassName1 + '\'' +
                ", mClassName2='" + mClassName2 + '\'' +
                ", mClassName3='" + mClassName3 + '\'' +
                ", mClassName4='" + mClassName4 + '\'' +
                ", mClassName5='" + mClassName5 + '\'' +
                '}';
    }
}
