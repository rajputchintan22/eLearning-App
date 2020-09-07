package com.elearn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModulesQuery extends AsyncTask<String , Void, String>  {



    Connection con;
    Context mContext;
    OnModulesLoad mCallback;
    ResultSet resultSet;

    String modules[][];

    boolean error=false;


    public ModulesQuery(Context context){
        mContext=context;
        mCallback=(OnModulesLoad) context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionString = "jdbc:mysql://192.168.43.9:3306/" + "elearn" + "?user=" + "elearn" + "&password=" + "batman" + "&useUnicode=true&characterEncoding=UTF-8&";
            Connection con = DriverManager.getConnection(connectionString);

            modules=new String[strings.length][];

            for(int i=0;i<strings.length;i++){
                Log.d("moduleslak",""+strings[i]);
                String query="select name from module where courseid="+strings[i]+" order by modulerank asc;";

                Statement stmt=con.createStatement();
                ResultSet resultSet=stmt.executeQuery(query);
                int j=0,size=0;
                while(resultSet.next()){
                    size++;
                }
                resultSet.beforeFirst();
                modules[i]=new String[size];
                while(resultSet.next()){
                    modules[i][j++]=resultSet.getString("name");
                }


            }




            String email = strings[0];
        }catch (Exception e){
            e.printStackTrace();
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
            mCallback.onModulesLoad(modules);
        }
    }
}
