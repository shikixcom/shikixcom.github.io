package com.example.mrsteve.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Button btn;
    private EditText EditTextUser, EditTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));
        }


        tv = (TextView) findViewById(R.id.tcl);
        btn = (Button) findViewById(R.id.btnnext);
        EditTextUser = (EditText) findViewById(R.id.EditTextUser);
        EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);

        tv.setOnClickListener(this);
        btn.setOnClickListener(this);


    }

    private void userLogin() {
        String email = EditTextUser.getText().toString().trim();
        String passsword = EditTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //user is empty
            Toast.makeText(this, "Please enter email user", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(passsword)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok
        //we will first show a progressdialog

        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email, passsword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));
                        }

                    }
                });


    }


    @Override
    public void onClick(View v) {
        if (v == btn) {
            userLogin();
        }

        if (v == tv) {
            finish();
            startActivity(new Intent(this, ActivityRegister.class));
        }
    }
}
