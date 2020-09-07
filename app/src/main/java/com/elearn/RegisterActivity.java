package com.elearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements OnRegistration {
    SharedPreferences preferences;
    String semail, spassword, sfname, slname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText fName = (EditText) findViewById(R.id.Fname);
        final EditText email = (EditText) findViewById(R.id.Register_email);
        final EditText lName = (EditText) findViewById(R.id.Lname);
        final EditText password = (EditText) findViewById(R.id.Register_Password);
        final EditText occupation = (EditText) findViewById(R.id.Occupation);
        final EditText confirmpass = (EditText) findViewById(R.id.Register_Confirmpass);

        Button RegisterToDatabase = (Button) findViewById(R.id.Register);


        RegisterToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input validation code
                if (fName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "First Name Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Email Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (lName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Last Name Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (occupation.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Occupation Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Password Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (confirmpass.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter Confirm Password", Toast.LENGTH_SHORT).show();

                } else {
                    semail = email.getText().toString();
                    spassword = password.getText().toString();
                    String scpassword=confirmpass.getText().toString();
                    if(spassword.equals(scpassword)){
                        sfname = fName.getText().toString();
                        slname = lName.getText().toString();
                        RegisterQuery q = new RegisterQuery(RegisterActivity.this);

                        q.execute(email.getText().toString(), password.getText().toString(), fName.getText().toString(), lName.getText().toString(), occupation.getText().toString());

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Passwords Do Not Match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    @Override
    public void onRegistration() {
        preferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor data = preferences.edit();
        data.putString("email", semail);
        data.putString("password", spassword);
        data.putString("fname", sfname);
        data.putString("lname", slname);

        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);


        data.commit();

    }
}
