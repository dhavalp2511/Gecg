package com.example.dhaval.gecg;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dhaval.gecg.pojo.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadEvent extends AppCompatActivity{

    private TextInputLayout name,organizer,time,date,faculty_contact,image,organizer_contact,location,description,cordinator;
    ImageView imageView;
    StorageReference mRootStorage;
    Uri filePath;
    String getUrl;
    private DatabaseReference mDatabase;
    private final int PICK_IMAGE_REQUEST=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_event);

        mDatabase=FirebaseDatabase.getInstance().getReference("events");
        mRootStorage = FirebaseStorage.getInstance().getReference("Images");
        final String pushKey=mDatabase.push().getKey();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        imageView = findViewById(R.id.photoUpload);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();

            }
        });
        Button submit=findViewById(R.id.event_button);
        image.setEnabled(false);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String g1=organizer_contact.getEditText().getText().toString();
                String g2=cordinator.getEditText().getText().toString();
                String g3=faculty_contact.getEditText().getText().toString();
                String g4=date.getEditText().getText().toString();
                String g5=description.getEditText().getText().toString();
                String g7=location.getEditText().getText().toString();
                String g8=name.getEditText().getText().toString();
                String g9=organizer.getEditText().getText().toString();
                String g10=time.getEditText().getText().toString();
                if(g1.length()>0){
                    if (g1.matches("[0-9]+") && g1.length() == 10) {
                        if(g2.length()>0){
                            if(g3.length()>0){
                                if (g3.matches("[0-9]+") && g3.length() == 10) {
                                    if(g4.length()>0){
                                        if(g5.length()>0){
                                            if(g7.length()>0){
                                                if(g8.length()>0){
                                                    if(g9.length()>0){
                                                        if(g10.length()>0){
                                                            if(filePath.toString().length()>0){
                                                                Event event=new Event(g1,g2,g3,g4,g5,getUrl,g7,g8,g9,g10);

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
                                                            }else{
                                                                Toast.makeText(UploadEvent.this, "select the image first", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }else{
                                                            Toast.makeText(UploadEvent.this, "Enter Pls Time", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else{
                                                        Toast.makeText(UploadEvent.this, "Enter pls Organizer Name", Toast.LENGTH_SHORT).show();
                                                    }
                                                }else{
                                                    Toast.makeText(UploadEvent.this, "Enter pls name of event", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(UploadEvent.this, "Enter pls location", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(UploadEvent.this, "Enter pls Description", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(UploadEvent.this, "Enter pls date", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(UploadEvent.this, "faculty Contact Should be in 10 digits", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(UploadEvent.this, "Enter Pls Faculty Cordinator Contact", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(UploadEvent.this, "Enter pls faculty Cordinator", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(UploadEvent.this, "Org. Contact Should be in 10 digits", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UploadEvent.this, "Org. Contact is empty", Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    public void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            image.getEditText().setText(filePath.toString());

                mRootStorage.child("Images" + UUID.randomUUID().toString()).putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        getUrl = task.getResult().getDownloadUrl().toString();



                    }
                });



        }
    }


}
