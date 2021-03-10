package com.example.assignmen3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.assignmen3.DataBase.DataBaseHelper;

// This fragment used for Floating button in Main activity

public class InsertCourseDialogFragment extends DialogFragment {


    protected EditText courseTitleEditText;
    protected EditText courseCodeEditText;
    protected Button saveCourseButton;
    protected Button cancelCourseButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_course,container,false);

        courseTitleEditText = view.findViewById(R.id.assignmentTitleEditText);
        courseCodeEditText = view.findViewById(R.id.assignmentGradeEditText);

        saveCourseButton = view.findViewById(R.id.saveCourseButton);
        cancelCourseButton = view.findViewById(R.id.cancelCourseButton);

        // Save Button

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = courseTitleEditText.getText().toString();
                String code = courseCodeEditText.getText().toString();

                DataBaseHelper db = new DataBaseHelper(getActivity());
                if (!(title.equals("") || code.equals(""))) {

                    // insert course to database

                    db.insertCourse(new Course(title,code));
                    ((MainActivity)getActivity()).loadListView();
                    getDialog().dismiss();
                }
            }
        });

        // Cancel Button

        cancelCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

            }
        });




        return view;
    }
}
