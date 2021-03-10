package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignmen3.DataBase.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected ListView coursesListView;
    protected FloatingActionButton addCourseButton;
    protected TextView averageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        coursesListView = findViewById(R.id.coursesListView);
        addCourseButton = findViewById(R.id.addCourseButton);
        averageTextView = findViewById(R.id.averageTextView);

        averageTextView.setText("Average of all assignments: "+(float) dbHelper.averageAllAssignments());
        loadListView();

        // the floating button to add a new course

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertCourseDialogFragment dialog = new InsertCourseDialogFragment();

                dialog.show(getSupportFragmentManager(),"InsertCourseFragment");

            }
        });


    }

    // List View Handler that shows courses and average

    protected void loadListView()
    {

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Course> courses = dbHelper.getAllCourses();

        final ArrayList<String> coursesListText = new ArrayList<String>();

        for(int i = 0; i < courses.size(); i++)
        {
            String temp = "";
            temp += courses.get(i).getTitle() + "\n";
            temp += courses.get(i).getCode() + "\n\n";
            temp += "Assignment Average = " + dbHelper.averageByCourseID(courses.get(i).getID());

            coursesListText.add(temp);
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesListText);

        coursesListView.setAdapter(arrayAdapter);
        dbHelper.close();

        // make list clickable that passes Course ID

        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToAssignment = new Intent(getApplicationContext(),assignmentActivity.class);
                Bundle b = new Bundle();
                b.putInt("CourseID",courses.get(position).getID());
                goToAssignment.putExtra("CourseID",courses.get(position).getID());


                startActivity(goToAssignment);
            }
        });


    }
}