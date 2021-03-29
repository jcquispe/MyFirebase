package com.example.juanky.myfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    public static final String PATH_START = "start";
    public static final String PATH_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView firebaseMessage = findViewById(R.id.firebaseMessage);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://my-firebase-243a9-default-rtdb.firebaseio.com/");
        final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firebaseMessage.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al consultar en Firebase", Toast.LENGTH_LONG).show();
            }
        });
    }
}