package com.elearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity implements TaskCompleted{

    String semail,spassword;

    SharedPreferences preferences;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

                final EditText  email=(EditText)findViewById(R.id.Email);
                final EditText  password=(EditText)findViewById(R.id.password);
                final Button login = (Button)findViewById(R.id.login);
                Button register = (Button)findViewById(R.id.RegisterActivity);
                imageView=(ImageView)findViewById(R.id.loginlogo);

                Picasso.get().load("http://sannibh.in/img/logo.png").error(R.drawable.ic_network_error).into(imageView);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    semail=email.getText().toString();
                    spassword=password.getText().toString();
                    if(semail.length()==0 || spassword.length()==0){
                        Toast.makeText(getApplicationContext(),"Please enter credentials",Toast.LENGTH_SHORT).show();
                    }
                    else{
                    LoginQuery q = new LoginQuery(LoginActivity.this);
                    q.execute(email.getText().toString(), password.getText().toString());
                    }


                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register_activity = new Intent(v.getContext(),RegisterActivity .class);
                    startActivity(register_activity);
                }
            });

    }

    @Override
    public void onTaskComplete(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        if(result=="success"){
            Intent i = new Intent(getApplicationContext(),CommonActivity.class);
            preferences=getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
            SharedPreferences.Editor data = preferences.edit();
            data.putString("email", semail);
            data.putString("password", spassword);
            data.commit();
            ActivityCompat.finishAffinity(this);
            startActivity(i);
        }
        else if(result=="fail")
        {
            Toast.makeText(getApplicationContext(),"Invalid Email Or Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
        }

    }
}
