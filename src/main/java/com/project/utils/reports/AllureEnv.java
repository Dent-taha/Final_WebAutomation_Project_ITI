package com.project.utils.reports;

import com.google.common.collect.ImmutableMap;
import com.project.utils.ConstantPaths;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;

import java.io.File;
import java.util.Objects;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnv {



    public static void setAllureEnvVariables()
    {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS",System.getProperty("os.name"))
                        .put("Java Version", System.getProperty("java.runtime.version"))
                        .put("Browser Type", Objects.requireNonNull(PropertyReader.getProperty("browserType")))
                        .build(), String.valueOf(ConstantPaths.RESULTS)+ File.separator
        );
        logsManager.info("allure environment variables have set  successfully");
    }
}
