package com.example.semesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class signup extends AppCompatActivity {
    Button b1,b2 ;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1 = findViewById(R.id.SignUp);
        b2 = findViewById(R.id.gotologinpage);
        mAuth = FirebaseAuth.getInstance();
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        EditText email = findViewById(R.id.SignUpEmail);  // Replace with user's entered email
        EditText password =   findViewById(R.id.PasswordSignup); // Replace with user's entered password
        String EnterdEmail = email.getText().toString().trim();
        String EnterdPassword = password.getText().toString().trim();

        createUserWithEmailAndPassword(EnterdEmail, EnterdPassword);
    }
});
b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent switchNow = new Intent(signup.this,MainActivity.class);
                          startActivity(switchNow);
    }
});

    }
    private void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Authentication success
                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent switchNow = new Intent(signup.this,home.class);
//                            startActivity(switchNow);

                            Toast.makeText(signup.this, user.getEmail() + " Signed Up",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            if ( task.getException() instanceof FirebaseAuthUserCollisionException) {
                                // Authentication failed
                                Toast.makeText(signup.this, "User already exists",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(signup.this, "Authentication Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}