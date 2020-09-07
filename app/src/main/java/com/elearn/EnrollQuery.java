package com.elearn;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EnrollQuery  extends AsyncTask<String , Void, String> {
    Connection con;
    Context mContext;
    OnEnrollment mCallback;
    ResultSet resultSet;
    ArrayList<CourseModel> courseModelList;
    boolean error=false;

    public EnrollQuery(Context mContext){
        this.mContext=mContext;
        mCallback=(OnEnrollment) mContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionString = "jdbc:mysql://192.168.43.9:3306/" + "elearn" + "?user=" + "elearn" + "&password=" + "batman" + "&useUnicode=true&characterEncoding=UTF-8&";
            Connection con = DriverManager.getConnection(connectionString);

            String email=strings[0];
            String courseid=strings[1];

            String query="insert into usertocourse values('"+email+"',"+courseid+");";
            Statement statement=con.createStatement();
            int i=statement.executeUpdate(query);


        }catch (Exception e){
            e.printStackTrace();
            error=true;
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(error){
            Toast.makeText(mContext,"Something went wrong",Toast.LENGTH_LONG).show();
        }
        else{
        mCallback.onEnrollment();
        }
    }
}
