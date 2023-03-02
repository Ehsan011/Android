package com.example.doctorlagbe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookingActivity extends AppCompatActivity {
    EditText lbname, lbaddress, lbpincode, lbcontact;
    Button buttonBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_booking);
        // Mapping id
        lbname = findViewById(R.id.labBookUserName);
        lbaddress = findViewById(R.id.labBookUserAddress);
        lbpincode = findViewById(R.id.labBookUserPinCode);
        lbcontact = findViewById(R.id.labBookUserContact);

        buttonBooking = findViewById(R.id.btnLabBooking);

//get intent by CartLabActivity
        Intent intent=getIntent();
        String[] price = intent.getStringExtra("amount").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");

//        Booking Work

        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db=new Database(getApplicationContext(), "healthcare", null, 1);
                db.addOrder(username, lbname.getText().toString(), lbaddress.getText().toString(), lbcontact.getText().toString(), Integer.parseInt(lbpincode.getText().toString()), date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab"  );
                db.removeCart(username,"lab");

                Toast.makeText(getApplicationContext(), "Booking is Done successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookingActivity.this, HomeActivity.class));
            }
        });


    }



}