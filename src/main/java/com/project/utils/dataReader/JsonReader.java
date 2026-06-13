package com.project.utils.dataReader;

import com.jayway.jsonpath.JsonPath;
import com.project.utils.ConstantPaths;
import com.project.utils.logs.logsManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {


 String jsonReader;
 String jsonFilename;

public JsonReader(String jsonFilename)
{
    this.jsonFilename=jsonFilename;
    try {
        String data_File_path= ConstantPaths.RESOURCES.resolve("test-data").resolve(jsonFilename+".json").toString();
        JSONObject data=(JSONObject) new JSONParser().parse(new FileReader(data_File_path));
        jsonReader=data.toJSONString();
    } catch (Exception e) {

        logsManager.error("Error  in founding  file  ", e.getMessage());
    }
}

public  String getJasonKey(String key)
{
    try {
        return JsonPath.read(jsonReader,key);
    }
    catch (Exception e)
    {
        return e.getMessage();
    }
}
}
