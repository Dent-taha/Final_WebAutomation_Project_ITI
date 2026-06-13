package com.project.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConstantPaths {
    public static final Path USER_DIR=Paths.get(System.getProperty("user.dir"), File.separator);
    public static final Path USER_HOME=Paths.get(System.getProperty("user.home"),File.separator);


    public static final Path TEST_OUTPUT=USER_DIR.resolve("test-output");


    public static final Path RESOURCES= Paths.get("src/main/resources");
    public static final Path SCREENSHOTS=TEST_OUTPUT.resolve("ScreenShots");


    // result ,  single  report, full report  path

    public static final Path RESULTS=TEST_OUTPUT.resolve("allure-results");
    public static final Path SINGLE_REPORT= TEST_OUTPUT.resolve("single-report");
    public static final Path FULL_REPORT=TEST_OUTPUT.resolve("full-report");

    // History
public static final Path RESULT_HISTORY=Paths.get(String.valueOf(RESULTS),"history",File.separator);
public static final  Path REPORT_HISTORY=FULL_REPORT.resolve("history");
public static final  Path LOGS=Paths.get(String.valueOf(TEST_OUTPUT),"Logs",File.separator);

        // extensions utils
    public static final String INDEX_HTML="index.html";
    public static final String REPORT_PREFIX="AllureReport_";
    public static final String REPORT_EXTENSION=".html";


}
