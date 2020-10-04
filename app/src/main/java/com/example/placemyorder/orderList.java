package com.example.placemyorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class orderList extends AppCompatActivity {

    TextView txtName, txtQuantity, txtAddress;
    String name;



    private OrderItemListClass ord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_list );

        Intent intent=getIntent();

        name=intent.getStringExtra( orderForm.EXTRA_MESSAGE );
        txtName=findViewById( R.id.viewProductName );
        txtQuantity=findViewById( R.id.view_quantity );
        txtAddress=findViewById( R.id.viewAddress );


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        Query q1=database.getReference( "OrderItemListClass" ).child( name );

        q1.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.hasChildren()) {
                txtName.setText( snapshot.child( "productName" ).getValue().toString() );
                txtQuantity.setText( snapshot.child( "quantity" ).getValue().toString() );
                txtAddress.setText( snapshot.child( "address" ).getValue().toString() );
            } else {
                Toast.makeText( getApplicationContext() , "No source to display" , Toast.LENGTH_SHORT ).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    public void delete(View view1){
        DatabaseReference refde2;
        refde2=FirebaseDatabase.getInstance().getReference( "OrderItemListClass" ).child( name );
        refde2.removeValue();
        Toast.makeText( getApplicationContext() , "Deleted successfully" , Toast.LENGTH_SHORT ).show();
        Intent intent=new Intent( this , orderForm.class );
        startActivity( intent );




    }
}