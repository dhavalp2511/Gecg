package com.example.dhaval.gecg;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dhaval.gecg.pojo.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadEvent extends AppCompatActivity {

    private TextInputLayout name,organizer,time,date,faculty_contact,image,organizer_contact,location,description,cordinator;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_event);

        mDatabase=FirebaseDatabase.getInstance().getReference("events");
        final String pushKey=mDatabase.push().getKey();

        name=findViewById(R.id.event_name);
        organizer=findViewById(R.id.event_organizer);
        time=findViewById(R.id.event_time);
        date=findViewById(R.id.event_date);
        faculty_contact=findViewById(R.id.event_faculty_no);
        image=findViewById(R.id.event_image);
        location=findViewById(R.id.event_location);
        organizer_contact=findViewById(R.id.event_cordinator_no);
        description=findViewById(R.id.event_description);
        cordinator=findViewById(R.id.event_cordinator);

        Button submit=findViewById(R.id.event_button);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String g1=name.getEditText().getText().toString();

                Event event=new Event(g1,g1,g1,g1,g1,g1,g1,g1,g1,g1);

                mDatabase.child(pushKey).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(UploadEvent.this,"Succefully Uploaded",Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(UploadEvent.this,"Error",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

    }
}
