package com.viriminfotech.utilities;

public class Constants {


    public static String PROJECT_API = "http://192.168.45.209:4300/project";
    public static String SIGN_IN_API = "http://192.168.45.209:4300/login";

    public static double MOUSE_X_POSITION = 0.0;
    public static double MOUSE_Y_POSITION = 0.0;

    public static final int TIMER_DELAY = 1000;
   // public static final int TIMER_REPEAT_TIME = 180000;
    public static final int TIMER_REPEAT_TIME = 1000;
    public static final int IMAGE_CAPTURE_TIMER = 360000;

    public static final String PROJECT_NAME = "VLogger";
    public static final String SIGN_IN = "Sign In";
    public static final String START_TIMER = "Start Timer";
    public static final String STOP_TIMER = "Stop Timer";
    public static final String PLEASE_SELECT_PROJECT = "Please Select Project";
    public static final String PROJECTS = "Projects";
    public static final String MEETING = "Meeting";
    public static final String DEVELOPMENT = "Development";
    public static final String TESTING = "Testing";
    public static final String ANALYSIS_AND_PLANNING = "Analysis and Planning";
  //  public static final String  = "VLogger";



    // check the operating system
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);
    public static boolean IS_MAC = (OS.indexOf("mac") >= 0);
    public static boolean IS_UNIX = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    public static boolean IS_SOLARIS = (OS.indexOf("sunos") >= 0);

    public static String formatSecondDateTime(int second) {
        if(second <= 0)return "";
        int h = second / 3600;
        int m = second % 3600 / 60;
        int s = second % 60; // Less than 60 is the second, enough 60 is the minute
        return h+"h "+m+"m "+s+"s";
    }
}
