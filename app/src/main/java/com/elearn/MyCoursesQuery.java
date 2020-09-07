package com.elearn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MyCoursesQuery extends AsyncTask<String , Void, String> {



    Connection con;
    Context mContext;
    OnCoursesload mCallback;
    ResultSet resultSet;

    boolean error=false;


    public MyCoursesQuery(Context context){
        mContext=context;
        mCallback=(OnCoursesload) context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionString = "jdbc:mysql://192.168.43.9:3306/" + "elearn" + "?user=" + "elearn" + "&password=" + "batman" + "&useUnicode=true&characterEncoding=UTF-8&";
            Connection con = DriverManager.getConnection(connectionString);

            String email=strings[0];



            String query="select course.courseid,course.coursename,course.image from usertocourse inner join course on course.courseid=usertocourse.courseid where usertocourse.email=\""+email+"\";";

            Log.d("sqllak",""+query);

            Statement stmt=con.createStatement();
            resultSet=stmt.executeQuery(query);

            Log.d("sqllak","here3");





        }catch (Exception e){
            e.printStackTrace();
            Log.d("sqllak", Log.getStackTraceString(e));
            error=true;
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("sqllak","here");
        if(error){
            Toast.makeText(mContext,"Something went wrong",Toast.LENGTH_LONG).show();
        }
        else{
        mCallback.onCoursesLoad(resultSet);
        }

    }
}
