package com.elearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;


    public static final String MyPREFERENCES = "UserPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.finishAffinity(this);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString("email", "0");
        String password = preferences.getString("password", "0");
        if (email == "0" && password == "0" || (email.length()==0 || password.length()==0) || !preferences.contains("email")) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            //Toast.makeText(this, "" + email + "" + password, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,CommonActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ActivityCompat.finishAffinity(this);
            startActivity(i);
        }


    }
}

