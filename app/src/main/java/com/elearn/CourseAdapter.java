package com.elearn;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.elearn.MainActivity.MyPREFERENCES;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {


    private Context mContext;
    private List<CourseModel> courseslist;
    SharedPreferences preferences;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView thumbnail;
        public Button overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description= (TextView) view.findViewById(R.id.description);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (Button) view.findViewById(R.id.overflow);
        }
    }

    public CourseAdapter(Context mContext, List<CourseModel> albumList) {
        this.mContext = mContext;
        this.courseslist = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courses_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CourseModel courseModel = courseslist.get(position);
        holder.title.setText(courseModel.getCouresname());
        holder.description.setText(courseModel.getDescription());

        // loading album cover using Glide library

        Picasso.get().load("http://192.168.43.9:80/sannibh/admin/"+courseModel.getUrl()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String email = preferences.getString("email", "0");
                Log.d("enrollquery",""+email);
                EnrollQuery enrollQuery=new EnrollQuery(mContext);
                enrollQuery.execute(email,courseModel.getCourseid());
                Toast.makeText(mContext,"clicked!"+courseModel.getCouresname(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseslist.size();
    }


}
