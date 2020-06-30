package technovation.tuty_boxapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.app.DialogFragment;
import android.widget.CheckBox;
import android.widget.RadioButton;
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

import java.util.Calendar;

import static technovation.tuty_boxapp.R.id.LLsubjects;

public class AddTeacherDetails extends AppCompatActivity {


    private static  final String TAG = "AddTeacherDetails";
    private FirebaseAuth mAuth;
    private CheckBox checkBox;
    private RadioButton radioButton;
    private String userID;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_details);
    }

    public void setTime(View v){
        DialogFragment newFragment = new TimePickerFragment();
        RadioButton rb = (RadioButton) v;
        newFragment.show(getFragmentManager(),"TimePicker");
    }

    private class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(),this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);
            //Set a message for user
            //Display the user changed time on TextView
            tv.setText("\n" + tv.getText()+ "Hour : " + String.valueOf(hourOfDay)
                    + "\nMinute : " + String.valueOf(minute) + "\n");
        }
    }

    void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void finishRegistration(View view){

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference myRef;

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();
        //handle the exception if the EditText fields are null
        myRef.child("users").child(userID).child("teacher").setValue( true );
        myRef.child("users").child(userID).child("student").setValue( false );
        toastMessage("New Information has been saved.");

        String job = "teacher";
        AddSubjectsToDatabase(myRef, job);
        radioButton = (RadioButton) findViewById( R.id.rBwhenPosted );
        if (radioButton.isChecked()){
            myRef.child("users").child(userID).child("teacher").child("demand").setValue( "whenPosted" );
        }
        else{
            myRef.child("users").child(userID).child("teacher").child("demand").setValue( hour + ":" + minute );
        }
        Intent intent = new Intent(this, FinishingSignUp.class);
        startActivity(intent);
    }

    public void AddSubjectsToDatabase(DatabaseReference myRef, String job) {
        checkBox = (CheckBox) findViewById( R.id.checkBoxMath );

        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Math").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxPhysics );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Physics").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxCS );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("ComputerScience").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxChemistry );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Chemistry").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxBio );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Bio").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxRo );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Romanian").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxEng );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("English").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxSpanish );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Spanish").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxItalian );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Italian").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxArts );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Arts").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxOther );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("Other").setValue( true );
        }

        checkBox = (CheckBox) findViewById( R.id.checkBoxGerm );
        if (checkBox.isChecked()){
            myRef.child("users").child(userID).child(job).child("German").setValue( true );
        }
    }
}
