package com.example.henilsarang.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mainscreen extends AppCompatActivity {
    private Toolbar mainToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
      // mainToolbar = findViewById(R.id.main_toolbar);
       //setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Sign Up");


    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){
            Intent loginintent = new Intent(mainscreen.this,MainActivity.class);
            startActivity(loginintent);
            finish();
        }

    }
}
