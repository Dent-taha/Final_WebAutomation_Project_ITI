package com.project.utils;

import com.project.utils.logs.logsManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    private static final String userDir=System.getProperty("user.dir")+File.separator;

    // rename  file
    public  static  void rename (String oldPath,String newPath)
    {

        try {
            var targetFile=new File(oldPath);
            String targetDirectory=targetFile.getParentFile().getAbsolutePath();
            File newFile=new File(targetDirectory+File.separator+newPath);
            if(!targetFile.getPath().equals(newFile.getPath()))
            {
                org.apache.commons.io.FileUtils.copyFile(targetFile,newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                logsManager.info(" Target File path: "+oldPath+" was renamed ");
            }
            else {
                logsManager.info("target  file  already  has  desired  name");
            }
        } catch (Exception e) {
        logsManager.error("Error  renaming  file  :",oldPath,"  or  file  is  not exist ");
        }
    }

    //  creating  directory

    public  static void createDir(String path)
    {
        try{
            File newDir = new File( path);
            if (!newDir.exists())
            {
                newDir.mkdirs();
                logsManager.info("successful creating Dir:",path);
            }
        } catch (Exception e) {
            logsManager.error("Error  in  creating directory",  path);
        }

    }

    //  delete dirs

    public static   void cleanDir(File file  )
    {
        try
        {
            org.apache.commons.io.FileUtils.deleteQuietly(file);
            logsManager.info("Deleting ", file.getAbsolutePath());

        } catch (Exception e) {
            logsManager.error("failed to clean  dir ", file.getAbsolutePath(),e.getMessage());
        }
    }

    //  check  if  file  exist in downloads
    public static boolean isFileExist(String fileName,int numberOfTries)
    {
        boolean isFileExisted=false;
        int i=0;
        while (i<numberOfTries && !isFileExisted)
        {
            try {
                Path filePath=ConstantPaths.RESOURCES.resolve(fileName);
                isFileExisted= Files.exists(filePath);
            }
            catch (Exception e)
            {
                logsManager.error(e.getMessage());
            }
            if(!isFileExisted)
            {
                try {
                    Thread.sleep(500);
                }
                catch (Exception e)
                {
                    logsManager.error(e.getMessage());
                }

            }
            i++;
        }
        return isFileExisted;

    }
}
