package com.example.gerard.socialapp.view.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowPostActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        author = findViewById(R.id.author);



        String postKey= getIntent().getStringExtra("POSTKEY");

        mRef = FirebaseDatabase.getInstance().getReference();

        mRef.child("posts").child("data").child(postKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);

                author.setText(post.author);
            }

            // View -> TextView
            // casting

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
