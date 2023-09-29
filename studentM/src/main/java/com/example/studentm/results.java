package com.example.studentm;

public class results {
    private int react;
    private int java;
    private int csharp;
    private int total;
    private int avg;
    private String grade;

    private String name;
    public results (int react, int java, int csharp, int total, int avg, String grade,String name) {
        this.react = react;
        this.java = java;
        this.csharp = csharp;
        this.total= total;
        this.avg = avg;
        this.grade = grade;
        this.name = name;
    }

    public int getReact() {
        return react;
    }

    public int getJava() {
        return java;
    }

    public int getCsharp() {
        return csharp;
    }

    public int getTotal() {
        return total;
    }

    public int getAvg() {
        return avg;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }
}
