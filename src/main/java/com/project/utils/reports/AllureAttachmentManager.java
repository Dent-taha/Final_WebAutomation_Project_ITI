package com.project.utils.reports;

import com.project.utils.logs.logsManager;
import io.qameta.allure.Allure;

import java.nio.file.Files;
import java.nio.file.Path;

public class AllureAttachmentManager {

public  static  void attachScreenShots(String name,Path path)
{
    try{
        //Path screenShoot = Path.of(path);
        if(Files.exists(path))
        {
            Allure.addAttachment(name,Files.newInputStream(path));
        }
        else {
            logsManager.error("screen shot not  found");
        }

    }
 catch (Exception e) {
        logsManager.error("failed  to  attach  screen shot");
    }


}
}
