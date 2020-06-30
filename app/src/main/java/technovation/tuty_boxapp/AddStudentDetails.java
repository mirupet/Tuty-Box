package technovation.tuty_boxapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.PendingIntent.getActivity;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class AddStudentDetails extends AppCompatActivity {

    private static  final String TAG = "AddStudentDetails";
    EditText grade;
    String gradeStr;
    RadioButton rb;
    private String userID;
    private FirebaseAuth mAuth;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_details);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    final CharSequence[] items = {"Beginner", "Experienced", "Advanced"};
    Map < Object, String > pair = new HashMap < Object, String >();

    public void setAptitude(View view){
        final CheckBox checkBox = (CheckBox)view;

        if(checkBox.isChecked()) {


            AlertDialog.Builder builder = new AlertDialog.Builder(AddStudentDetails.this);
            builder.setTitle("Choose your level");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    pair.put(checkBox, (String) items[item] );
                }
            });

            builder.setCancelable(false);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void finishRegistration(View view){
        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference myRef;

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        myRef = mFirebaseDatabase.getReference();
        userID = user.getUid();

        grade = (EditText) findViewById( R.id.editTextGrade );
        gradeStr = grade.getText().toString();


        myRef.child("users").child(userID).child("student").setValue( true );
        myRef.child("users").child(userID).child("teacher").setValue( false );

        rb = (RadioButton) findViewById( R.id.rbMorning );
        if (rb.isChecked()){
            myRef.child("users").child(userID).child("student").child("program").setValue( "Morning" );
        }
        else
            myRef.child("users").child(userID).child("student").child("program").setValue( "Noon" );

        myRef.child("users").child(userID).child("student").child("grade").setValue( gradeStr );

        AddSubjectsAptitudeToDatabase( myRef, "student" );
        Intent intent = new Intent(this, FinishingSignUp.class);
        startActivity(intent);
    }

    public void AddSubjectsAptitudeToDatabase(DatabaseReference myRef, String job) {
        checkBox = (CheckBox) findViewById( R.id.checkBoxMath );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Math").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxPhysics );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Physics").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxCS );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("ComputerScience").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxChemistry );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Chemistry").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxBio );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Bio").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxRo );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Romanian").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxEng );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("English").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxSpanish );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Spanish").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxItalian );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Italian").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxArts );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Arts").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxOther );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Other").setValue( pair.get(checkBox) );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxGerm );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("German").setValue( pair.get(checkBox) );
        }
    }
}
