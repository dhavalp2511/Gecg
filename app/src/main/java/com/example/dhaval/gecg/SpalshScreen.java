package com.example.dhaval.gecg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Timer;
import java.util.TimerTask;

public class SpalshScreen extends AppCompatActivity {
    long Delay = 3000;
    DatabaseReference mToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer RunSplash = new Timer();

        mToken= FirebaseDatabase.getInstance().getReference("tokens");

        String token_id= FirebaseInstanceId.getInstance().getToken();

        String pushKey=mToken.push().getKey();

        mToken.child(token_id).setValue("true");

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SpalshScreen.this,HomePage.class));
                }
            };
        RunSplash.schedule(ShowSplash, Delay);
    }
}
