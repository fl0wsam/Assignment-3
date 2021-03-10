package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.assignmen3.DataBase.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class assignmentActivity extends AppCompatActivity {

    protected ListView assignmentsListView;
    protected FloatingActionButton addAssignmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        //get Course ID that passed by MainActivity
        Bundle b = getIntent().getExtras();
        int CourseID = -1;
        CourseID = b.getInt("CourseID");

        assignmentsListView = findViewById(R.id.assignmentsListView);
        addAssignmentButton = findViewById(R.id.addAssignmentButton);

        loadListView(CourseID);

        // Making add Button

        addAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertAssignmentDialogFragment dialog = new InsertAssignmentDialogFragment();

                dialog.show(getSupportFragmentManager(),"InsertAssignmentFragment");

            }
        });
        //*******************




    }

    // Show the ListView

    protected void loadListView(int CourseID)
    {

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Assignment> assignments = dbHelper.getAllAssignmentsByCourseID(CourseID);

        final ArrayList<String> assignmentListText = new ArrayList<String>();

        for(int i = 0; i < assignments.size(); i++)
        {
            String temp = "";
            temp += assignments.get(i).getTitle() + "\n\n";
            temp += assignments.get(i).getGrade();

            assignmentListText.add(temp);
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assignmentListText);

        assignmentsListView.setAdapter(arrayAdapter);
        dbHelper.close();


    }
    //*************

    //this method used for fragment to put assignments for specific course

    protected int getCourseID(){
        Bundle b = getIntent().getExtras();
        int CourseID = -1;
        CourseID = b.getInt("CourseID");
        return 1;
    }
    //********************

}