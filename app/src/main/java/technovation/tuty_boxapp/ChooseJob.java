package technovation.tuty_boxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseJob extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_job);
    }

    public void addTeacherDetails(View view){
        Intent intent = new Intent(this, AddTeacherDetails.class);
        startActivity(intent);
    }

    public void addStudentDetails(View view){
        Intent intent = new Intent(this, AddStudentDetails.class);
        startActivity(intent);
    }

    public void addBothDetails(View view){
        Intent intent2 = new Intent(this, AddBothStudentDetails.class);
        startActivity(intent2);
    }

}
