package com.example.studentm;

public class student {
    String course;
    String description;
    String degree;
    public student(String course, String description,String degree){
        super();
        this.course= course;
        this.description=description;
        this.degree=degree;

    }

    public String getCourse() {
        return course;
    }

    public String getDescription() {
        return description;
    }

    public String getDegree() {
        return degree;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

