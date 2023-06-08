package com.example.semesterproject;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;



public class MainActivity extends AppCompatActivity {
     Button b1,b2 ;

    private FirebaseAuth mAuth;
   public EditText email,password;
    public  String EnteredEmail,EnteredPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.LoginButton);
        b2=findViewById(R.id.SignupButton);
        mAuth = FirebaseAuth.getInstance();



        b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        email = findViewById(R.id.username);  // Replace with user's entered email
          password =   findViewById(R.id.password); // Replace with user's entered password
          EnteredEmail = email.getText().toString().trim();
          EnteredPassword = password.getText().toString().trim();

        if (EnteredEmail.length()<=0 || EnteredPassword.length()<=0){
            Toast.makeText(MainActivity.this, "Please enter Valid Credentials",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, EnteredEmail+""+EnteredPassword,
                    Toast.LENGTH_SHORT).show();
            signInWithEmailAndPassword(EnteredEmail, EnteredPassword);
        }


    }
});
b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent signup = new Intent(MainActivity.this, signup.class);
        startActivity(signup);
    }
});
    }

    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Authentication success
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent switchNow = new Intent(MainActivity.this,home.class);
                            switchNow.putExtra("user",EnteredEmail);
                            startActivity(switchNow);
                            finish();

                        } else {
                            // Authentication failed
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}