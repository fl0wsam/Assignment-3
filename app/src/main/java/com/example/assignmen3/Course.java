package com.example.assignmen3;

// Course Class

public class Course {

    // Variables

    private String Title;
    private String Code;
    private Integer ID;

    // Constructors

    public Course(Integer ID, String title, String code) {
        this.ID = ID;
        Title = title;
        Code = code;
    }

    public Course(String title, String code) {
        Title = title;
        Code = code;
    }

    // Getters and Setters

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}