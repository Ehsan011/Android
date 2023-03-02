package com.example.doctorlagbe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUserName, edEmail, edPassword, edConfirm;
    Button button;
    TextView regTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUserName = findViewById(R.id.labBookUserName);
        edEmail = findViewById(R.id.labBookUserAddress);
        edPassword = findViewById(R.id.labBookUserPinCode);
        edConfirm = findViewById(R.id.labBookUserContact);
        button = findViewById(R.id.btnLabBooking);
        regTv = findViewById(R.id.textViewExistingUser);

        regTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String conPass = edConfirm.getText().toString();
                Database db= new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || conPass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
                } else {
//                    if (password.compareTo(conPass) == 0) {
//                        if(isValidPass(password)){
//                                db.register(username,email, password);
//                                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 Characters, having letter, digit and special symbol ", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
//                    }

                    db.register(username,email, password);
                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });
    }
//    Password Validation Start
    public static boolean isValidPass(String password){
        int p1=0,p2=0, p3=0;

        if(password.length() < 8){
            return false;
        }
        else {
            for (int p=0; p< password.length(); p++){
                if(Character.isLetter(password.charAt(p))){
                    p1=1;
                }
            }
            for (int r=0; r< password.length(); r++){
                if(Character.isDigit(password.charAt(r))){
                    p2=1;
                }
            }
            for (int s=0; s< password.length(); s++){
                char c=password.charAt(s);
                if(c>=33 && c<=46 || c == 64){
                    p3=1;
                }
            }
            if (p1==1 && p2==1 && p3==1)
                return true;
        }
        return false;
    }
//    Pass Validation End

}