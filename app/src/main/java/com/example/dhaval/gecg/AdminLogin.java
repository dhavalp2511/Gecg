package com.example.dhaval.gecg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    private Button login;
    private TextInputLayout username,password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login=findViewById(R.id.login_button);
        username=findViewById(R.id.admin_username);
        password=findViewById(R.id.admin_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar=findViewById(R.id.progressbar_login);
        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String name=username.getEditText().getText().toString();
                String pass=password.getEditText().getText().toString();
                if(name.length()>0){
                    if(pass.length()>0){
                        mAuth.signInWithEmailAndPassword(name,pass).
                                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if(task.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            startActivity(new Intent(AdminLogin.this,UploadEvent.class));

                                        }else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(AdminLogin.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(AdminLogin.this,"Enter pls password",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AdminLogin.this,"Enter pls Email",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
