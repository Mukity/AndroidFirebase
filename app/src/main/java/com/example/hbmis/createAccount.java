package com.example.hbmis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class createAccount extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText Email,Password;
    private Button submit,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pass = Password.getText().toString();
                if (email.isEmpty()) {
                    Email.setError("Provide your E-mail");
                    Email.requestFocus();
                } else if (pass.isEmpty()) {
                     Password.setError("Set your password");
                     Password.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                     Toast.makeText(createAccount.this, "Fields Empty!", Toast.LENGTH_LONG).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                     mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(createAccount.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!task.isSuccessful()) {
                                 Toast.makeText(createAccount.this.getApplicationContext(),
                                         "SignUp unsuccessful: " + task.getException().getMessage(),
                                         Toast.LENGTH_LONG).show();
                             } else {
                                 startActivity(new Intent(createAccount.this, MainActivity.class));
                             }
                         }
                     });
                } else {
                     Toast.makeText(createAccount.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
