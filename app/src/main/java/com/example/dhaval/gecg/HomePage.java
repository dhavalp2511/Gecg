package com.example.dhaval.gecg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.dhaval.gecg.pojo.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mFirebaseDatabase;
    List<Event> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        eventList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("events");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();

                    Event event = dataSnapshot.getValue(Event.class);
                    eventList.add(event);

                recyclerView.setAdapter(new DataAdapter(getApplicationContext(),eventList));
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
