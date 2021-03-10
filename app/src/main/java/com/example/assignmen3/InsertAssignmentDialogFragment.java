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

// This fragment used for Floating button in Assignment activity

public class InsertAssignmentDialogFragment extends DialogFragment {


    protected EditText assignmentTitleEditText;
    protected EditText assignmentGradeEditText;
    protected Button saveCourseButton;
    protected Button cancelCourseButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_assignment,container,false);

        assignmentTitleEditText = view.findViewById(R.id.assignmentTitleEditText);
        assignmentGradeEditText = view.findViewById(R.id.assignmentGradeEditText);

        saveCourseButton = view.findViewById(R.id.saveCourseButton);
        cancelCourseButton = view.findViewById(R.id.cancelCourseButton);

        // Save Button

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = assignmentTitleEditText.getText().toString();
                int grade = Integer.valueOf(assignmentGradeEditText.getText().toString());
                DataBaseHelper db = new DataBaseHelper(getActivity());
                if (!(title.equals("") || grade == -1)) {

                    // insert Assignment to database

                    db.insertAssignment(new Assignment(title,grade,((assignmentActivity)getActivity()).getCourseID()));
                    ((assignmentActivity)getActivity()).loadListView(((assignmentActivity)getActivity()).getCourseID());
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