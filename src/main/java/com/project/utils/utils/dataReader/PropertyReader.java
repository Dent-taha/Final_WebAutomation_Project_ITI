package com.project.utils.utils.dataReader;

import com.project.utils.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {


    public static Properties loadProperties()
    {
        Properties properties=new Properties();
        Collection<File> propertiesFile;
        propertiesFile= FileUtils.listFiles(new File("src/main/resources"),new String[]{"properties"},true);
        propertiesFile.forEach(file -> {
            try {
                properties.load(new FileInputStream(file));
            }
            catch (Exception e)
            {
                logsManager.error("file  not found", e.getMessage());
            }
            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);
        });
        return properties;
    }

public static String getProperty(String key)
{
    try {
      return   System.getProperty(key);
    } catch (Exception e) {
        logsManager.error("error  in getting property  for    key:",key," ",e.getMessage());
        return null;
    }

}
}
