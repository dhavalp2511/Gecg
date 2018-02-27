package com.example.dhaval.gecg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dhaval.gecg.pojo.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomePage extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mFirebaseDatabase,mToken;

    List<Event> eventList;
    List<String> idList;
    DataAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Gecg");

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.ENGLISH).format(new Date());

        mToken= FirebaseDatabase.getInstance().getReference("tokens");

        String token_id= FirebaseInstanceId.getInstance().getToken();

        if (token_id != null) {
            mToken.child(token_id).setValue(timeStamp);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = new ArrayList<>();
        idList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("events");

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(HomePage.this, ActivityDetails.class);
                i.putExtra("activityId",idList.get(position));
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                idList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    idList.add(dataSnapshot1.getKey());
                    Event event = dataSnapshot1.getValue(Event.class);
                    eventList.add(event);
                }

                dataAdapter = new DataAdapter(getApplicationContext(), eventList);
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_search){
            startActivity(new Intent(HomePage.this,AdminLogin.class));
            return  true;
        }else{
            return super.onOptionsItemSelected(item);
        }

//        switch (item.getItemId()) {
//            case R.id.action_search:
//                // search action
//                return true;
//
//                default:
//
//
//        }
    }
}

