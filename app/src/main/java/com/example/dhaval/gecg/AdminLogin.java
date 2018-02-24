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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    private Button login;
    private TextInputLayout username,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login=findViewById(R.id.login_button);
        username=findViewById(R.id.admin_username);
        password=findViewById(R.id.admin_password);

        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getEditText().getText().toString();
                String pass=password.getEditText().getText().toString();


                mAuth.signInWithEmailAndPassword(name,pass).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    startActivity(new Intent(AdminLogin.this,UploadEvent.class));

                                }else {
                                    Toast.makeText(AdminLogin.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

}
