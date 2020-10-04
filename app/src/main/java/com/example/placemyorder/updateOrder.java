package com.example.placemyorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class updateOrder extends AppCompatActivity {

    EditText txtName,txtQuantity,txtAddress;
    DatabaseReference dbRefer;
    Button update_btn;

    private OrderItemListClass order;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_order );
        order=new OrderItemListClass();

        txtName=(EditText) findViewById( R.id.editTextTextPersonName4 );
        txtQuantity=(EditText) findViewById( R.id.Quantity );
        txtAddress=(EditText) findViewById( R.id.address );
        update_btn=(Button) findViewById( R.id.updateBtn2 );
        order=new OrderItemListClass();
        dbRefer=FirebaseDatabase.getInstance().getReference().child( "OrderItemListClass" );

    }

    public void onDataChange(DataSnapshot dataSnapshot){
        if (dataSnapshot.hasChild("ord")){
            try{
                order.setProductName(txtName.getText().toString().trim());
                order.setQuantity(Integer.parseInt(txtQuantity.getText().toString().trim()));
                order.setAddress(txtAddress.getText().toString().trim());
                dbRefer = FirebaseDatabase.getInstance().getReference().child("OrderItemListClass");

                dbRefer.setValue(order);
                Toast.makeText(getApplicationContext(), "data updated successfully", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }






    };




}