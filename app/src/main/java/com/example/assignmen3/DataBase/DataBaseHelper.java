package com.example.assignmen3.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.assignmen3.Assignment;
import com.example.assignmen3.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/*
This is DatabaseHelper
note: in every method used try and catch for exception handling
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String TAG = "DataBaseHelper";

    public DataBaseHelper(Context context) {

        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create the Course Table

        String CREATE_TABLE_COURSE = "CREATE TABLE " + Config.COURSE_TABLE_NAME +
                " (" + Config.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                + Config.COLUMN_COURSE_CODE + " TEXT NOT NULL)";

        Log.d(TAG, CREATE_TABLE_COURSE);

        db.execSQL(CREATE_TABLE_COURSE);

        Log.d(TAG, "Course db created.");

        // Create the Assignment Table

        String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE " + Config.ASSIGNMENT_TABLE_NAME +
                " (" + Config.COLUMN_ASSIGNMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_COURSE_ID + " INTEGER, " + Config.COLUMN_ASSIGNMENT_TITLE + " TEXT NOT NULL, "
                + Config.COLUMN_ASSIGNMENT_GRADE + " INTEGER)";

        Log.d(TAG, CREATE_TABLE_ASSIGNMENT);

        db.execSQL(CREATE_TABLE_ASSIGNMENT);

        Log.d(TAG, "Assignment db created.");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // this method creates a new course in database table

    public long insertCourse(Course course) {
        // This method will create a new course
        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        // get tables values

        contentValues.put(Config.COLUMN_COURSE_TITLE, course.getTitle());
        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCode());

        //Exception Handler

        try {

            id = db.insertOrThrow(Config.COURSE_TABLE_NAME, null, contentValues);
        } catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            db.close();

        }
        return id;


    }

    //get all courses from database

    public List<Course> getAllCourses() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(Config.COURSE_TABLE_NAME, null, null, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    List<Course> courses = new ArrayList<>();

                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));

                        courses.add(new Course(id, title, code));

                    } while (cursor.moveToNext());

                    return courses;
                }
            }
        } catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();

        }

        return Collections.emptyList();


    }

    // get a course by its code but not using in this project

    public Course getCourseByCode(String codeCourse) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(Config.COURSE_TABLE_NAME, null, Config.COLUMN_COURSE_CODE + "= ?", new String[]{codeCourse},null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));
                        return new Course(id,title,code);
                    }

            }

        } catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
            return null;

    }

    // this method creates a new course in database table

    public long insertAssignment(Assignment assignment) {

        // This method will create a new assignment

        long id = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //get table values

        contentValues.put(Config.COLUMN_ASSIGNMENT_TITLE, assignment.getTitle());
        contentValues.put(Config.COLUMN_ASSIGNMENT_GRADE, assignment.getGrade());
        contentValues.put(Config.COLUMN_COURSE_ID,assignment.getCourseID());

        //Exception Handler and put assignment in table

        try {

            id = db.insertOrThrow(Config.ASSIGNMENT_TABLE_NAME, null, contentValues);
        } catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            db.close();

        }
        return id;


    }

    //get all assignments by a Course ID

    public List<Assignment> getAllAssignmentsByCourseID (int Course_ID) {
        List<Assignment> assignments = new ArrayList<Assignment>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {

            //COLUMN COURSE ID = Course_ID is the selection option for getting database values

            cursor = db.query(Config.ASSIGNMENT_TABLE_NAME,
                    null,
                    Config.COLUMN_COURSE_ID + " = " + Course_ID,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    do {

                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_TITLE));
                        int grade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));
                        assignments.add(new Assignment(title, grade, id, Course_ID));
                    } while (cursor.moveToNext());
                    return assignments;
                }

            }


        }catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return Collections.emptyList();
    }

    //Calculate average of a Course by its Course ID

    public float averageByCourseID (int Course_ID){
        float sum = 0;
        List<Assignment> assignments = new ArrayList<Assignment>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(Config.ASSIGNMENT_TABLE_NAME,
                    null,
                    Config.COLUMN_COURSE_ID + " = " + Course_ID,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    do {

                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_TITLE));
                        int grade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));
                        assignments.add(new Assignment(title, grade, id, Course_ID));
                    } while (cursor.moveToNext());

                }

            }
            for(int i = 0; i<assignments.size(); i++){
                sum += assignments.get(i).getGrade();
            }
            return sum/assignments.size();


        }catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return 0;
    }

    // Calculate average of all assignments for main activity

    public float averageAllAssignments (){
        float sum = 0;
        List<Assignment> assignments = new ArrayList<Assignment>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(Config.ASSIGNMENT_TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    do {

                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_TITLE));
                        int grade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));
                        assignments.add(new Assignment(title, grade, id));
                    } while (cursor.moveToNext());

                }

            }
            for(int i = 0; i<assignments.size(); i++){
                sum += assignments.get(i).getGrade();
            }
            return sum/assignments.size();


        }catch (SQLException e) {
            Log.d(TAG, "EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed: " + e, Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return 0;
    }
}


