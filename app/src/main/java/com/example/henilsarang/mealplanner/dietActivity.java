package com.example.henilsarang.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class dietActivity extends AppCompatActivity {



    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private Button veg,non_veg;
    private String currentuser,diet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        mAuth = FirebaseAuth.getInstance();
        veg = findViewById(R.id.btnveg);
        non_veg=findViewById(R.id.btnNonveg);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();



        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dietIntent = new Intent(dietActivity.this,mealActivity.class);
                startActivity(dietIntent);

                diet = "veg";

                currentuser = mAuth.getCurrentUser().getUid().toString();
                //1 - Create child in root object
                //2 - Assign some value to the child object

                mDatabaseReference.child(currentuser).child("diet").setValue(diet);
              //  mDatabaseReference.child(currentuser).child("Password").setValue(pass);




            }
        });



        non_veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dietIntent = new Intent(dietActivity.this,mealActivity.class);
                startActivity(dietIntent);

                diet = "non-veg";

                currentuser = mAuth.getCurrentUser().getUid().toString();
                //1 - Create child in root object
                //2 - Assign some value to the child object

                mDatabaseReference.child(currentuser).child("diet").setValue(diet);
                //  mDatabaseReference.child(currentuser).child("Password").setValue(pass);




            }
        });


    }
}
