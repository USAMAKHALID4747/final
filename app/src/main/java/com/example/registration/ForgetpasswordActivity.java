package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetpasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnResetpassword;
    private EditText ResetEmailInput;
 @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        mAuth=FirebaseAuth.getInstance();
        btnResetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String userEmail = ResetEmailInput.getText().toString();

                if (TextUtils.isEmpty(userEmail))
                {
                    Toast.makeText(ForgetpasswordActivity.this, "please write your valid email address first..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                          if(task.isSuccessful())
                          {
                              Toast.makeText(ForgetpasswordActivity.this, "Please check your Email Account,if you want to reset your password..", Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(ForgetpasswordActivity.this, LoginActivity.class));
                          }
                          else
                          {
                              String message =task.getException().getMessage();
                              Toast.makeText(ForgetpasswordActivity.this, "Error Occurred" + message, Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
                }
            }
        });

    }
}