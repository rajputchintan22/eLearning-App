package com.elearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseExpandableListAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.elearn.MainActivity.MyPREFERENCES;


public class CommonActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,OnCoursesload,OnModulesLoad,OnAllCoursesLoad,OnEnrollment {
    private TabHost tabHost;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;
    private boolean doubleBackToExitPressedOnce;

    private FloatingActionButton fab;


    SharedPreferences preferences;
    ArrayList<String> mycourses=new ArrayList<>();
    ArrayList<String> images=new ArrayList<>();

    WrapperExpandableListAdapter wrapperAdapter;

    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        init();
        
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void init() {



        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString("email", "0");

        MyCoursesQuery myCoursesQuery=new MyCoursesQuery(CommonActivity.this);
        myCoursesQuery.execute(email);

        AllCoursesQuery allCoursesQuery=new AllCoursesQuery(CommonActivity.this);
        allCoursesQuery.execute(email);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.removeAllTabs();

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Courses"));
        tabLayout.addTab(tabLayout.newTab().setText("My Courses"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Initializing view pager adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(viewPagerAdapter);

        //Adding onTabSelectedListener to swipe views
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){});
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
           // Toast.makeText(this, "You Clicked On Profile button", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Profile.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCoursesLoad(ResultSet result) {

        Log.d("sqllak","here2");
        try {
            mycourses=new ArrayList<>();
            images=new ArrayList<>();
            ArrayList<String> courseidlist=new ArrayList<>();
            while (result.next()) {
                String coursename=result.getString("coursename");
                String image=result.getString("image");
                String courseid=result.getString("courseid");
                mycourses.add(coursename);
                images.add(image);
                courseidlist.add(courseid);


                Log.d("sqllak","Courseid:"+courseid+" Course:"+coursename+" Image:"+image);
            }


            Log.d("sqllak","arraylength"+mycourses.size());
            Log.d("sqllak","arraylist:"+mycourses.get(0));


            String courseid[]=new String[courseidlist.size()];
            for(int i=0;i<courseidlist.size();i++){
                courseid[i]=courseidlist.get(i);
            }

            ModulesQuery modulesQuery=new ModulesQuery(CommonActivity.this);
            modulesQuery.execute(courseid);




           // myList.setAdapter(wrapperAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public WrapperExpandableListAdapter getCourses(){

        return wrapperAdapter;
    }

    @Override
    public String[][] onModulesLoad(String[][] modules) {


        BaseExpandableListAdapter myAdapter = new MyAdapter(getApplicationContext(),mycourses,images,modules);
        wrapperAdapter = new WrapperExpandableListAdapter(myAdapter);

        Log.d("sqllak","here4");

        Tab1.setAdatper(wrapperAdapter);


        return new String[0][];
    }

    @Override
    public void onAllCoursesLoad(ArrayList<CourseModel> courseModel) {
        Log.d("allcourses","oncallback");


        Tab2.prepareAlbums(courseModel);

    }

    @Override
    public void onEnrollment() {
        //Toast.makeText(getApplicationContext(),"Enrollment done",Toast.LENGTH_SHORT).show();
        init();

    }
}
