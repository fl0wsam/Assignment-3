package com.example.assignmen3;

// Assignment Class

public class Assignment {

    //Variables

    private String Title;
    private Integer Grade;
    private Integer ID;
    private Integer CourseID;

    // Constructors

    public Assignment(String title, Integer grade, Integer ID, Integer courseID) {
        Title = title;
        Grade = grade;
        this.ID = ID;
        CourseID = courseID;
    }

    public Assignment(String title, Integer grade, Integer courseID) {
        Title = title;
        Grade = grade;
        CourseID = courseID;
    }

    //Getters and Setters

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCourseID() {
        return CourseID;
    }

    public void setCourseID(Integer courseID) {
        CourseID = courseID;
    }
}

