package com.example.computersolutions.Activity.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.computersolutions.Activity.UserProfileActivity;
import com.example.computersolutions.Model.PaymentModel;
import com.example.computersolutions.R;
import com.example.computersolutions.Util;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etCardNumber, etExpirationDate, etBillingAddress, etPostalCode;
    Button btnSignUp;
    Spinner spnPaymentType;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    PaymentModel paymentModel;
    ArrayAdapter<CharSequence> spinnerAdapter;
    String paymentTypeStr;
    Util util;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        util = new Util(PaymentActivity.this);

        spnPaymentType = findViewById(R.id.paymentMethod);
        etCardNumber = findViewById(R.id.cardNo);
        etExpirationDate = findViewById(R.id.expireDate);
        etBillingAddress = findViewById(R.id.address);
        etPostalCode = findViewById(R.id.postalCode);
        btnSignUp = findViewById(R.id.btnSignUp);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.paymentTypes, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPaymentType.setAdapter(spinnerAdapter);
        spnPaymentType.setOnItemSelectedListener(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentModel = new PaymentModel(paymentTypeStr,
                        etCardNumber.getText().toString(),
                        etExpirationDate.getText().toString(),
                        etBillingAddress.getText().toString(),
                        etPostalCode.getText().toString());
                if (verifyPaymentModel(paymentModel)) {
                    alertDialog = util.createLoadingAlert();
                    alertDialog.show();
                    databaseReference.child("Payment").child(firebaseUser.getUid()).setValue(paymentModel)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    alertDialog.cancel();
                                    startActivity(new Intent(PaymentActivity.this, UserProfileActivity.class));
                                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    alertDialog = util.createDescriptionAlert("Error in updating payment details");
                                    alertDialog.show();
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    private boolean verifyPaymentModel(PaymentModel paymentModel){
        if (paymentModel.getCardNumber().isEmpty()) {
            etCardNumber.setError("Credit Card Number is Required.");
            return false;
        }

        if (paymentModel.getExpirationDate().isEmpty()) {
            etExpirationDate.setError("Expiration date is Required.");
            return false;
        }

        if (paymentModel.getBillingAddress().length() < 6) {
            etBillingAddress.setError("Expiration date is Required.");
            return false;
        }

        if (paymentModel.getPostalCode().isEmpty()) {
            etPostalCode.setError("Postal Code is Required.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        paymentTypeStr = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        paymentTypeStr = adapterView.getItemAtPosition(0).toString();
    }
}