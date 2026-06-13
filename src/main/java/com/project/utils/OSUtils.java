package com.project.utils;

public class OSUtils {

    public enum OS{
        WINDOWS,
        LINUX,
        MAC,
        OTHER
    }

public static OS getCurrentOS()
{
    String os=System.getProperty("os.name").toLowerCase();
    if(os.contains("wind")){return OS.WINDOWS;}
    if(os.contains("mac"))return OS.MAC;
    if (os.contains("nix")|| os.contains("nux")) return OS.LINUX;
    return OS.OTHER;
}
}
