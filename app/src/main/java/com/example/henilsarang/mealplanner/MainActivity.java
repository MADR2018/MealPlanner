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

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText pwdText;
    public Button btnlogin;
    public FirebaseAuth mAuth;
    private ProgressBar mProgressBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.emailText);
        pwdText = findViewById(R.id.pwdText2);
        btnlogin = findViewById(R.id.btnLogin);
        mProgressBar = findViewById(R.id.loginProgress);
        mAuth = FirebaseAuth.getInstance();


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String loginEmail = emailText.getText().toString();
                String loginpwd = pwdText.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginpwd))
                {
                 Toast.makeText(getApplicationContext(),"Working",Toast.LENGTH_SHORT).show();

                        mProgressBar.setVisibility(View.VISIBLE);


                    mAuth.signInWithEmailAndPassword(loginEmail,loginpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Toast.makeText(getApplicationContext(),"yes!!!!!",Toast.LENGTH_SHORT).show();


                            if (task.isSuccessful())
                            {
                                //sentToMain();
                            }
                            else{
                               String errorMsg = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"ERROR : " + errorMsg,Toast.LENGTH_SHORT).show();
                                mProgressBar.setVisibility(View.INVISIBLE);

                            }
                        }
                    });


                }

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
        Intent mainintent = new Intent(MainActivity.this,mainscreen.class);

        startActivity(mainintent);
        finish();

    }


}

