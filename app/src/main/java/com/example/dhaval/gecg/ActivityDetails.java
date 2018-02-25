package com.example.dhaval.gecg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dhaval.gecg.pojo.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityDetails extends AppCompatActivity {
    String actId;
    DatabaseReference mDatabaseReference;
    ReadMoreTextView description;
    ImageView actImage;
    TextView activityname,destination,activityTiming,activityDate,cordinatorFacultyContact,cordinatorFaculty,organizerName,organizerContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        activityname = findViewById(R.id.actName);
        destination = findViewById(R.id.actDestName);
        description = findViewById(R.id.readmore);
        activityTiming = findViewById(R.id.actTiming);
        activityDate = findViewById(R.id.actDate);
        cordinatorFacultyContact = findViewById(R.id.actCordFacContact);
        cordinatorFaculty = findViewById(R.id.actCordFac);
        organizerName = findViewById(R.id.actOrgName);
        organizerContact = findViewById(R.id.actOrgContact);
        actImage = findViewById(R.id.actImage);
        actId = getIntent().getExtras().getString("activityId");
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("events");

        mDatabaseReference.child(actId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    activityname.setText(dataSnapshot.child("name").getValue().toString());
                    destination.setText(dataSnapshot.child("location").getValue().toString());
                    description.setText(dataSnapshot.child("description").getValue().toString());
                    activityTiming.setText(dataSnapshot.child("time").getValue().toString());
                    activityDate.setText(dataSnapshot.child("date").getValue().toString());
                    cordinatorFaculty.setText(dataSnapshot.child("cordinator").getValue().toString());
                    cordinatorFacultyContact.setText(dataSnapshot.child("cordinatorContactNo").getValue().toString());
                    organizerContact.setText(dataSnapshot.child("contactNo").getValue().toString());
                    organizerName.setText(dataSnapshot.child("organizer").getValue().toString());
                    Glide
                            .with(getApplicationContext())
                            .load(dataSnapshot.child("image").getValue().toString())// can also be a drawable
                            .error(R.mipmap.ic_launcher) // will be displayed if the image cannot be loaded
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .crossFade()
                            .into(actImage);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
