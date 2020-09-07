package com.elearn;

public class CourseModel {
    String courseid;
    String couresname;
    String description;
    String url;

    public  CourseModel(){}

    public CourseModel(String courseid,String couresname,String description,String url){
        this.couresname=couresname;
        this.courseid=courseid;
        this.description=description;
        this.url=url;
    }

    public String getCourseid(){
        return courseid;
    }

    public String getCouresname(){
        return  couresname;
    }

    public String  getDescription(){
        return  description;
    }

    public String getUrl(){
        return url;
    }




}
