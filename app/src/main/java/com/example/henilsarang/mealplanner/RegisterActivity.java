package com.example.henilsarang.mealplanner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText email_field,pass_field,confirmpass_field;
    private Button reg_btn,back;
    private ProgressBar mProgress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_field= findViewById(R.id.emailtxt);
        pass_field= findViewById(R.id.passwordtxt);
        confirmpass_field= findViewById(R.id.confirmpasstxt);
        reg_btn = findViewById(R.id.btnSignUp);
        mProgress = findViewById(R.id.signupProgress);
        mAuth = FirebaseAuth.getInstance();
        back = findViewById(R.id.btnBack);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_field.getText().toString();
                String pass = pass_field.getText().toString();
                String confrim_pass = confirmpass_field.getText().toString();

                if(!TextUtils.isEmpty(email) &&  !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confrim_pass)){

                    if (pass.equals(confrim_pass)){

                        mProgress.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    Intent dietIntent = new Intent(RegisterActivity.this,dietActivity.class);
                                    startActivity(dietIntent);
                                    finish();

                                }else{

                                    String errorMessage = task.getException().getMessage();

                                    Toast.makeText(getApplicationContext(),"Error" + errorMessage,Toast.LENGTH_SHORT).show();
                                }

                                mProgress.setVisibility(View.INVISIBLE);

                            }
                        });

                    }
                    else
                    {Toast.makeText(getApplicationContext(),"Password doesn't match..Check the password",Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(RegisterActivity.this,WelcomeActivity.class);
                startActivity(mainIntent);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
/*
      FirebaseUser currentUser = mAuth.getCurrentUser();
      if(currentUser == null){
          sentToMain();
      }
*/

    }

    private void sentToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this,WelcomeActivity.class);
        startActivity(mainIntent);
        finish();

    }


}
