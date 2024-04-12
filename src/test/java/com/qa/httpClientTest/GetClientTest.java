package com.qa.httpClientTest;


import com.qa.base.TestBase;
import com.qa.httpClient.GetClient;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetClientTest extends TestBase {

 public  TestBase testBase;
    String url;
   String serviceurl;
    String apiurl;
    GetClient getclient;

    public GetClientTest() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        testBase = new TestBase();
         serviceurl = prop.getProperty("URL");
         apiurl = prop.getProperty("serviceUrl");

         url = serviceurl + apiurl;

           }


    @Test
            public void getAPITest() throws IOException {

            getclient=new GetClient();
            getclient.get(url);

    }


}
