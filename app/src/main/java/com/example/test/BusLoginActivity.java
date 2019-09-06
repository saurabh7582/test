package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class BusLoginActivity extends AppCompatActivity {
    // button & edittext declare
    private Button mLogin,mSing_up;
    private EditText mEmail,mPassword;
    public FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_login);


        // assinging value for button and edittext
        mEmail=(EditText) findViewById(R.id.Email_id);
        mPassword=(EditText) findViewById(R.id.password);
        mLogin=(Button) findViewById(R.id.login);
        mSing_up=(Button) findViewById(R.id.sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();

       mSing_up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view){

              mAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(BusLoginActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               if (task.isSuccessful()) {
                                   Toast.makeText(BusLoginActivity.this, "Authentication success",
                                           Toast.LENGTH_SHORT).show();
                                   updateUI();
                               } else {
                                   // If sign in fails, display a message to the user.
                                   Toast.makeText(BusLoginActivity.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();

                               }
                           }
                       });

           }
       });


       mLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               mAuth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(BusLoginActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   Toast.makeText(BusLoginActivity.this, "Authentication SUCCESS",
                                           Toast.LENGTH_SHORT).show();
                                   //Intent intent=new Intent(BusLoginActivity.this,Bus_details.class);
                                  // startActivity(intent);
                                   updateUI();
                               } else {
                                   // If sign in fails, display a message to the user.
                                   Toast.makeText(BusLoginActivity.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                                   updateUI();
                               }

                               // ...
                           }
                       });

             /* Intent intent=new Intent(BusLoginActivity.this,Bus_Location.class);
              startActivity(intent);

              */
           }
       });


    }

    private void updateUI() {

        FirebaseUser user=mAuth.getCurrentUser();

        if(user==null)
        {
            Toast.makeText(BusLoginActivity.this, "user login fail.",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(BusLoginActivity.this, user.getEmail(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI();
    }


    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI();
    }
}
