package com.project.utils.utils;

import com.project.utils.logs.logsManager;

public class TerminalUtils {


    public static void executeTerminalCommand(String... commandParts)
    {
        try
        {
            Process process = Runtime.getRuntime().exec(commandParts);

            String output =
                    new String(process.getInputStream().readAllBytes());

            String error =
                    new String(process.getErrorStream().readAllBytes());

            int exitCode = process.waitFor();

            logsManager.info("Exit Code: " + exitCode);
            logsManager.info("STDOUT: " + output);
            logsManager.info("STDERR: " + error);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
