package technovation.tuty_boxapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static java.security.AccessController.doPrivileged;
import static java.security.AccessController.getContext;

public class GeneralInformation extends AppCompatActivity {

    private static  final String TAG = "GeneralInformation";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general_information);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() > 9;
    }
    void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    public void pushSaveButton(View view) {

        final EditText first_name = (EditText) findViewById(R.id.textFirstName);
        EditText surname = (EditText) findViewById(R.id.textSurname);
        EditText description = (EditText) findViewById(R.id.textDescription);
        EditText phone_number = (EditText) findViewById(R.id.textPhoneNumber);

        final String first_nameStr = first_name.getText().toString();
        final String surnameStr = surname.getText().toString();
        final String phone_numberStr = phone_number.getText().toString();
        final String descriptionStr = description.getText().toString();


        if (first_nameStr.equals("")) {
            first_name.setError("The field is required!");
            return;
        }

        if (surnameStr.equals("")) {
            surname.setError("The field is required!");
            return;
        }

        if (phone_numberStr.equals("")) {
            phone_number.setError("The field is required!");
            return;
        }
        if (!isPhoneValid(phone_numberStr)) {
            phone_number.setError("The phone number is too short!");
            return;
        } else {
            //insert a new created user

            FirebaseDatabase mFirebaseDatabase;
            final DatabaseReference myRef;

            mAuth = FirebaseAuth.getInstance();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            final FirebaseUser user = mAuth.getCurrentUser();
            myRef = mFirebaseDatabase.getReference();

            userID = user.getUid();
            //handle the exception if the EditText fields are null
            Contact userInformation = new Contact(first_nameStr, surnameStr, descriptionStr, phone_numberStr);
            myRef.child("users").child(userID).setValue(userInformation);
            toastMessage("New Information has been saved.");

            Intent intent = new Intent( this, ChooseJob.class );
            startActivity( intent );
        }
    }
}