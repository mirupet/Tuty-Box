package technovation.tuty_boxapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    EditText mEmail, mPassword;
    Button btnSignIn, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        mEmail = (EditText) findViewById( R.id.email );
        mPassword = (EditText) findViewById( R.id.password );

        btnSignIn = (Button) findViewById( R.id.buttonSignIn );
        btnRegister = (Button) findViewById( R.id.buttonRegister );

        mAuth = FirebaseAuth.getInstance();
        final String email = mEmail.getText().toString();
        final String pass = mPassword.getText().toString();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d( TAG, "onAuthStateChanged:signed_in:" + user.getUid() );
                    toastMessage( "Successfully signed in with: " + user.getEmail() );
                    toastMessage( user.getEmail() );
                    Intent intent = new Intent( LoginActivity.this, CurrentClasses.class );
                    startActivity( intent );
                } else {
                    // User is signed out
                    Log.d( TAG, "onAuthStateChanged:signed_out" );
                    toastMessage( "Successfully signed out." );
                }
            }
        };
    }


    /*@Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/
    void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void goToGeneralInformation( View view ){

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference myRef;

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById( R.id.email );
        mPassword = (EditText) findViewById( R.id.password );
        final String email = mEmail.getText().toString();
        final String pass = mPassword.getText().toString();

        if (!checkEmail( email )){
            mEmail.setError( "The email is invalid!" );
            return;
        }

        if (!checkPass( pass )){
            mPassword.setError( "Password is too short!" );
            return;
        }
        mAuth.createUserWithEmailAndPassword( email, pass );

        Intent intent = new Intent( this, GeneralInformation.class );
        startActivity( intent );
    }

    public void goToCurrentClasses(View view){
        Intent intent = new Intent(this, CurrentClasses.class);
        startActivity( intent );
    }

    /// Checking functions
    private boolean checkPass(String pass) {
        if (pass.length() < 6)
            return false;
        return true;
    }
    private boolean checkEmail(String email) {
        if (!email.contains( "@" ))
            return false;
        return true;
    }
}

