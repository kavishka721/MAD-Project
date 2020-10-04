package com.example.computersolutions.Activity.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.computersolutions.R;
import com.example.computersolutions.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText etEmail;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    Button sendLink, backLogin;
    AlertDialog alertDialog;
    Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        util = new Util(ForgotPassword.this);

        etEmail = findViewById(R.id.reset);
        progressBar = findViewById(R.id.progressBar);
        backLogin = findViewById(R.id.backLoginBtn);
        sendLink = findViewById(R.id.sendLinkBtn);

        sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = util.createLoadingAlert();
                alertDialog.show();
                firebaseAuth.sendPasswordResetEmail(etEmail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                alertDialog.cancel();
                                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                            }
                        }
                    });
            }
        });

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}