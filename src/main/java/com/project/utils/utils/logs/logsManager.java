package com.project.utils.utils.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class logsManager {
/*
* make class name  appear  in  logs rather  than this  class
* */
private static Logger logger()
{
    return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
}

    public static void info(String... msg){logger().info(String.join(" ",msg));}
    public static void error(String... msg){logger().error(String.join(" ",msg));}
    public static void warn(String msg){logger().error(String.join(" ",msg));}
    public static void fatal(String msg){logger().error(String.join(" ",msg));}
    public static void debug(String msg){logger().error(String.join(" ",msg));}
}

