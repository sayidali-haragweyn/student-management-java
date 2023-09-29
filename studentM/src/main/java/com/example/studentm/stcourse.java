package com.example.studentm;

public class stcourse {
    int stid;
    String fname;
    String lname;
    String gender;
    String course;
    int year;
    public stcourse(int stid,String fname,String lname,String gender, String course, int year){
        super();
        this.stid=stid;
        this.fname=fname;
        this.lname=lname;
        this.gender=gender;
        this.course=course;
        this.year=year;
    }

    public int  getStid() {
        return stid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
