package com.example.fanogi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public void RegistrationMail (View View){
        EditText RegViaMailName = (EditText) findViewById(R.id.regMailName);
        EditText RegViaMailMail = (EditText) findViewById(R.id.regMail);
        EditText RegViaMailPass = (EditText) findViewById(R.id.regPass);
        String email = (String) RegViaMailMail.getText().toString();
        String password = (String) RegViaMailMail.getText().toString();
        if (RegViaMailMail.getText().toString().isEmpty()) {
            RegViaMailMail.setError("გთხოვთ შეიყვანოთ თქვენი მეილი");
            RegViaMailMail.requestFocus();
            return;
        }
        else if (RegViaMailPass.getText().toString().isEmpty()){
            RegViaMailPass.setError("გთხოვთ შეიყვანოთ თქვენი პაროლი");
            RegViaMailPass.requestFocus();
            return;
        }
        else if (RegViaMailName.getText().toString().isEmpty()){
            RegViaMailName.setError("გთხოვთ შეიყვანოთ თქვენი სახელი");
            RegViaMailName.requestFocus();
            return;
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent RegistrationIntent = new Intent(getApplicationContext(), Feed.class);
                                startActivity(RegistrationIntent);
                            } else {
                                Toast.makeText(Registration.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }


                        }
                    });


        }
    }


    public void GoLogIn(View View){
        Intent LogInIntent = new Intent(getApplicationContext(), LogIn.class);
        startActivity(LogInIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
    }
}