package technovation.tuty_boxapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;


public class AddBothStudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_both_student_details);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void setAptitude(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            final CharSequence[] items = {"Beginner", "Experienced", "Advanced"};
            AlertDialog.Builder builder = new AlertDialog.Builder(AddBothStudentDetails.this);
            builder.setTitle("Choose your level");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            builder.setCancelable(false);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void addTeacherDetails(View view){
        Intent intent = new Intent(this, AddTeacherDetails.class);
        startActivity(intent);
    }
}
