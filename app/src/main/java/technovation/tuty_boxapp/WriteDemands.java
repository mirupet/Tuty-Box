package technovation.tuty_boxapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class WriteDemands extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_demands);
            }

    public void sendDemand(View view){
        EditText title = (EditText) findViewById(R.id.textTitle);
        EditText description = (EditText) findViewById(R.id.textTitle);

        RadioGroup rgSubj = (RadioGroup) findViewById(R.id.Subjects);
        int rgSubjID = rgSubj.getCheckedRadioButtonId();
        if (rgSubjID == -1){
            openDialog("Choose a subject!");
            return;
        }else{
            RadioButton radioButton = (RadioButton) rgSubj.findViewById(rgSubjID);
            String subj = (String) radioButton.getText();
        }

        RadioGroup rgDiff = (RadioGroup) findViewById(R.id.Difficulty);
        int rgDiffID = rgDiff.getCheckedRadioButtonId();
        if (rgDiffID == -1){
            openDialog("Choose difficulty!");
            return;
        }else{
            RadioButton radioButton = (RadioButton) rgDiff.findViewById(rgSubjID);
            String diff = (String) radioButton.getText();
        }

        /// Send mail to all tutor users with the same subject checked

    }
    public void openDialog(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
