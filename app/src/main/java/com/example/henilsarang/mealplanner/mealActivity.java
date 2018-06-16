package com.example.henilsarang.mealplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class mealActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_meal);
        super.onCreate(savedInstanceState);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Recipes");
        mDatabaseReference.keepSynced(true);


        mRecyclerView = findViewById(R.id.recipe_recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<recipes,recipeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<recipes, recipeViewHolder>
                (recipes.class,R.layout.recipe_row,recipeViewHolder.class,mDatabaseReference) {
            @Override
            protected void populateViewHolder(recipeViewHolder viewHolder, recipes model, int position) {

            viewHolder.setTitle(model.getTitle());
            viewHolder.setImage(getApplication(),model.getImg());

            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    public static class recipeViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public recipeViewHolder(View itemView){
            super(itemView);
            mView= itemView;

        }
        public void setTitle(String title){
            TextView recipe_title = mView.findViewById(R.id.recipe_title);
            recipe_title.setText(title);
        }

        public void setImage(Context ctx,String image){
            ImageView recipe_image = mView.findViewById(R.id.recipe_image);
            Picasso.with(ctx).load(image).into(recipe_image);
        }
    }
}
