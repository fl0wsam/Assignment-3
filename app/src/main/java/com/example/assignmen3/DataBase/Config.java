package com.example.assignmen3.DataBase;
/*
This is the configuration for Database
every column has been named
 */

public class Config {

    public static final String DATABASE_NAME = "courses-db";
    public static final int DATABASE_VERSION = 1;

    public static final String COURSE_TABLE_NAME = "course";

    public static final String COLUMN_COURSE_ID = "Course_id";
    public static final String COLUMN_COURSE_TITLE = "title";
    public static final String COLUMN_COURSE_CODE = "code";

    public static final String ASSIGNMENT_TABLE_NAME = "assignment";
    public static final String COLUMN_ASSIGNMENT_ID = "Assignment_id";
    public static final String COLUMN_ASSIGNMENT_TITLE = "title";
    public static final String COLUMN_ASSIGNMENT_GRADE = "grade";
}
