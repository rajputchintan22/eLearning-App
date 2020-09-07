package com.elearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    SharedPreferences preferences;
    public static final String MyPREFERENCES = "UserPrefs";

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView username = (TextView) findViewById(R.id.profilename);
        ImageView imageView=(ImageView) findViewById(R.id.sannibghlogo);

        Picasso.get().load("http://sannibh.in/img/logo.png").into(imageView);

        logout = (Button) findViewById(R.id.Logout);

        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString("email", "0");
        String password = preferences.getString("password", "0");
        String firstname = preferences.getString("fname", "0");
        String lastname = preferences.getString("lname", "0");
        username.setText("Email:"+email+"\n");

        logout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor data = preferences.edit();


                data.putString("email", "");
                data.putString("password", "");
                data.putString("fname", "");
                data.putString("lname", "");

                data.commit();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                finishAffinity();

                startActivity(i);
            }
        });

    }
}
