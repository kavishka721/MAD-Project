package com.example.computersolutions.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.computersolutions.Activity.Authentication.LoginActivity;
import com.example.computersolutions.R;
import com.example.computersolutions.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {
    Button btnLogOut, btnDeleteAccount;
    TextView tvName, tvEmail, tvPaymentType, tvPayment, tvAddress, tvPostalCode;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("Payment");
    AlertDialog alertDialog;
    Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        util = new Util(UserProfileActivity.this);

        tvName = findViewById(R.id.tvUserProfileName);
        tvEmail = findViewById(R.id.tvUserProfileEmail);
        tvPaymentType = findViewById(R.id.tvUserProfilePaymentType);
        tvPayment = findViewById(R.id.tvUserProfilePayment);
        tvAddress = findViewById(R.id.tvUserProfileAddress);
        tvPostalCode = findViewById(R.id.tvUserProfilePostalCode);
        tvName.setText(firebaseUser.getDisplayName());
        tvEmail.setText(firebaseUser.getEmail());
        btnLogOut = findViewById(R.id.btnLogout);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        alertDialog = util.createLoadingAlert();
        alertDialog.show();

        databaseReference.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alertDialog.cancel();
                tvPaymentType.setText(dataSnapshot.child("paymentType").getValue().toString());
                tvPayment.setText(dataSnapshot.child("cardNumber").getValue().toString());
                tvAddress.setText(dataSnapshot.child("billingAddress").getValue().toString());
                tvPostalCode.setText(dataSnapshot.child("postalCode").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                alertDialog.cancel();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = util.createLoadingAlert();
                alertDialog.show();
                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            alertDialog.cancel();
                            startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                        }
                    }
                });
            }
        });
    }
}