package com.project.utils.utils;

import com.project.utils.logs.logsManager;

import java.io.File;

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
}
