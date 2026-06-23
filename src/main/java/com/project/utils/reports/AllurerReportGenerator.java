package com.project.utils.reports;

import com.project.utils.ConstantPaths;
import com.project.utils.OSUtils;
import com.project.utils.TerminalUtils;
import com.project.utils.TimeManager;
import com.project.utils.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AllurerReportGenerator {

//  generate Allure Report


public static  void generateAllureReport(boolean isSingleFile)
{

    Path outPutFolder=isSingleFile? ConstantPaths.SINGLE_REPORT:ConstantPaths.FULL_REPORT;
    List<String> command= new ArrayList<>(
            List.of(
            AllureBinaryManager.getExecutable().toString()
            , "generate"
            ,String.valueOf(ConstantPaths.RESULTS)
            ,"-o"
            ,outPutFolder.toString()
            ,"--clean")

    );
    if (isSingleFile)command.add("--single-file");
    TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
}
    public static void openReport(String FileName)
    {
        Path reportPath=ConstantPaths.SINGLE_REPORT.resolve(FileName);
        switch (OSUtils.getCurrentOS())
        {
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe","/c","start",reportPath.toString());
            case MAC,LINUX -> TerminalUtils.executeTerminalCommand("open","\""+reportPath+"\"");
            default -> logsManager.warn("opening  allure report  is  not supported in this os  ");
        }

    }

    //  copy history  folder  from report  to test result

    public static void copyHistoryToResultFolder()
    {
        try{
            FileUtils.copyDirectory(ConstantPaths.REPORT_HISTORY.toFile(), ConstantPaths.RESULT_HISTORY.toFile());
            logsManager.info("successfully  coping history  file");
        } catch (IOException e) {
            logsManager.error("Error in copy history file from report  to test result  folder");
        }
    }


      public static String renameReport()
    {
        String newName=ConstantPaths.REPORT_PREFIX+ TimeManager.getTimeStamp()+ConstantPaths.REPORT_EXTENSION;
        com.project.utils.FileUtils.rename(ConstantPaths.SINGLE_REPORT.resolve(ConstantPaths.INDEX_HTML).toString(),newName);
        return newName;
    }




}
