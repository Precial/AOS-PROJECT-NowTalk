package com.sg.nowtalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.sg.nowtalk.model.UserModel;

public class SingupActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText name;
    private Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        email = (EditText)findViewById(R.id.signupActivity_edditext_email);
        password = (EditText)findViewById(R.id.signupActivity_edditext_password);
        name = (EditText)findViewById(R.id.signupActivity_edditext_name);
        signup = (Button)findViewById(R.id.signupActivity_button_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 이메일, 패스워드, 이름 값이 NULL인 경우 진행하지 않도록함.
                if (email.getText().toString() == null || password.getText().toString() == null || name.getText().toString() == null ){
                    return;
                }

                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(SingupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                UserModel userModel = new UserModel();
                                userModel.userName = name.getText().toString();

                                String uid = task.getResult().getUser().getUid();
                                FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);
                              

                            }
                        });

            }
        });

    }

}