package com.elearn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AllCoursesQuery extends AsyncTask<String , Void, String> {

    Connection con;
    Context mContext;
    OnAllCoursesLoad mCallback;
    ResultSet resultSet;
    ArrayList<CourseModel> courseModelList;
    boolean error=false;

    public AllCoursesQuery(Context mContext){
        this.mContext=mContext;
        mCallback=(OnAllCoursesLoad) mContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionString = "jdbc:mysql://192.168.43.9:3306/" + "elearn" + "?user=" + "elearn" + "&password=" + "batman" + "&useUnicode=true&characterEncoding=UTF-8&";
            Connection con = DriverManager.getConnection(connectionString);

            String email=strings[0];

            String query="select * from course where courseid not in (select courseid from usertocourse where email='"+email+"')";
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            Log.d("allcourses","query");

            courseModelList=new ArrayList<>();
            while(resultSet.next()){
                String coursename=resultSet.getString("coursename");
                String courseid=resultSet.getString("courseid");
                String description=resultSet.getString("description");
                String image=resultSet.getString("image");
                CourseModel courseModel=new CourseModel(courseid,coursename,description,image);
                courseModelList.add(courseModel);
                Log.d("allcourses",""+coursename);
            }



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
        else {
        mCallback.onAllCoursesLoad(courseModelList);
        }
    }
}
