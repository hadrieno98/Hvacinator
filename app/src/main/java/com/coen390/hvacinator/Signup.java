package com.coen390.hvacinator;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText psw, mail;
    Button signup, signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(Signup.this);

        mAuth = FirebaseAuth.getInstance();

        psw = (EditText) findViewById(R.id.psw);
        mail = (EditText) findViewById(R.id.mail);

        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), Login.class);
                startActivity(s);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(mail.getText().toString(), psw.getText().toString())
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                updateUI(null);
                            }
                        });

            }
        });

    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(null);
    }
    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            findViewById(R.id.signup).setVisibility(View.GONE);
        } else {
            findViewById(R.id.signup).setVisibility(View.VISIBLE);
        }
    }

}

