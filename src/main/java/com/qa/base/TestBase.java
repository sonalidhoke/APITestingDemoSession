package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public int RESPONSE_STATUS_CODE_200=200;
    public int RESPONSE_STATUS_CODE_500=500;
    public int RESPONSE_STATUS_CODE_400=400;
    public int RESPONSE_STATUS_CODE_401=401;
    public int RESPONSE_STATUS_CODE_201=201;

    public Properties prop;

    public TestBase() throws FileNotFoundException {
        try{
            prop=new Properties();
            FileInputStream fp=new FileInputStream("src/main/java/com/qa/config/config.properties");
            prop.load(fp);

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
