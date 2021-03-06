package com.example.fanogi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    public void updateUI(FirebaseUser account){
    }
    private FirebaseAuth mAuth;
    public void Registration(View View){
        Intent RegistrationIntent = new Intent(getApplicationContext(), Registration.class);
        startActivity(RegistrationIntent);
    }
    public void LogLogIn(View View){
        EditText LogMail = (EditText) findViewById(R.id.LogMail);
        EditText LogPass = (EditText) findViewById(R.id.LogPass);
        String mail = LogMail.getText().toString();
        String password = LogPass.getText().toString();
        if (LogMail.getText().toString().isEmpty()){
            LogMail.setError("გთხოვთ შეიყვანოთ თქვენი მეილი!");
            LogMail.requestFocus();
            return;
        }
        else if (LogPass.getText().toString().isEmpty()){
            LogPass.setError("გთხოვთ შეიყვანოთ თქვენი პაროლი!");
            LogPass.requestFocus();
            return;
        }
        else{
            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                Intent FeedIntent = new Intent(getApplicationContext(), FeedActivity.class);
                                startActivity(FeedIntent);
                            } else {
                                Toast.makeText(LogIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
    }

}