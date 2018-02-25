package com.example.dhaval.gecg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    private TextInputLayout username,password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username=findViewById(R.id.admin_username);
        password=findViewById(R.id.admin_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button login=findViewById(R.id.login_button);
        progressBar=findViewById(R.id.progressbar_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mAuth=FirebaseAuth.getInstance();
                if(TextUtils.isEmpty(username.getEditText().getText().toString())){
                    Toast.makeText(AdminLogin.this,"Please Enter E-mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getEditText().getText().toString())) {
                    Toast.makeText(AdminLogin.this,"Please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(name,pass).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    startActivity(new Intent(AdminLogin.this,UploadEvent.class));
                                    progressBar.setVisibility(View.GONE);
                                }else {
                                    Toast.makeText(AdminLogin.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
