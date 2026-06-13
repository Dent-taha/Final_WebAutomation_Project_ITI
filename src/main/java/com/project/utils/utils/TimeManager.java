package com.project.utils.utils;

public class TimeManager {

    public static String getTimeStamp()
    {
        return   new  java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new java.util.Date());
    }
    public static String  getSimpleTimeStamp()
    {
        return   new  java.text.SimpleDateFormat("hh-mm-ss").format(new java.util.Date());
    }
}
