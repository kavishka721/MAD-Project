package com.example.placemyorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class orderForm extends AppCompatActivity {


    EditText itemName,quantity,address,name;
    DatabaseReference dbRef;
    Button confirm_btn,update_btn,showOrder_btn;
    public static final String EXTRA_MESSAGE ="com.example.placemyorder.MESSAGE";

    private OrderItemListClass order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_form );



        itemName = (EditText) findViewById(R.id.itemName);
        quantity = (EditText) findViewById(R.id.Quantity);
        address = (EditText) findViewById(R.id.address );
        confirm_btn = (Button) findViewById(R.id.confirmBtn );
        update_btn = (Button) findViewById(R.id.updateBtn );
        showOrder_btn = (Button) findViewById(R.id.showOrderBtn );
        name =( EditText)(findViewById(R.id.search_product ));


        order = new OrderItemListClass();
        dbRef = FirebaseDatabase.getInstance().getReference().child("OrderItemListClass");
        confirm_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                try {
                    if (TextUtils.isEmpty(itemName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter item name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(quantity.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter quantity", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter address", Toast.LENGTH_SHORT).show();
                    else {
                        order.setProductName(itemName.getText().toString().trim());
                        order.setQuantity(Integer.parseInt(quantity.getText().toString().trim()));
                        order.setAddress(address.getText().toString().trim());

                        dbRef.child(itemName.getText().toString()).setValue(order);

                        Toast.makeText(getApplicationContext(), "data entered successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = FirebaseDatabase.getInstance().getReference().child("OrderItemListClass");

                myRef3.addValueEventListener(new ValueEventListener() {

                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        order.setProductName(itemName.getText().toString().trim());
                        order.setQuantity(Integer.parseInt(quantity.getText().toString().trim()));
                        order.setAddress(address.getText().toString().trim());


                        dbRef=FirebaseDatabase.getInstance().getReference().child("OrderItemListClass").child(itemName.getText().toString());
                        dbRef.setValue(order);
                        Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT).show();
                    }

                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });



        showOrder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(orderForm.this,orderList.class);
                String masg = name.getText().toString();
                intent.putExtra(EXTRA_MESSAGE,masg);
                startActivity(intent);
            }
        });



    }
}