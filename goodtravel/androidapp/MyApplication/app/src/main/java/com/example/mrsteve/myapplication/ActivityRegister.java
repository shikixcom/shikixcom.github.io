package com.example.mrsteve.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

    private Button btnsignup;
    private EditText edUser, edPassword;
    private ProgressDialog progressDialog;
    private TextView TextViewHaveAcc;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnsignup = (Button) findViewById(R.id.btnreg);
        edUser = (EditText) findViewById(R.id.editText3);
        edPassword = (EditText) findViewById(R.id.editText4);
        TextViewHaveAcc = (TextView) findViewById(R.id.TextViewHaveAcc);

        btnsignup.setOnClickListener(this);
        TextViewHaveAcc.setOnClickListener(this);
    }

    private void registerUser(){
        String user = edUser.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        if (TextUtils.isEmpty(user)){
            //user is empty
            Toast.makeText(this, "Please enter email user", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok
        //we will first show a progressdialog

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));
                        } else {
                            Toast.makeText(ActivityRegister.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });


    }

    @Override
    public void onClick(View v) {

        if (v == btnsignup) {
            registerUser();
        }

        if (v == TextViewHaveAcc) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
