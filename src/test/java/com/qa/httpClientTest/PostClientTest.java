package com.qa.httpClientTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Data.Users;
import com.qa.base.TestBase;


import com.qa.httpClient.PostClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class PostClientTest extends TestBase {
    public PostClientTest() throws FileNotFoundException {
    }
    public  TestBase testBase;
    String url;
    String serviceurl;
    String apiurl;
    PostClient postClient;

    CloseableHttpResponse closeableHttpResponse;



    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        testBase = new TestBase();
        serviceurl = prop.getProperty("URL");
        apiurl = prop.getProperty("serviceUrl");

        url = serviceurl + apiurl;

    }

    @Test
    public void postAPITest() throws IOException {
        postClient=new PostClient();
        HashMap<String, String> headerMap= new HashMap<String, String>();
        headerMap.put("Content-type","application/json");

        //jacksonn API
        ObjectMapper mapper=new ObjectMapper();
        Users users=new Users("morpheus", "leader", "953", "2023-11-30T09:23:31.087Z"); //expected users object

        //object to json file
        mapper.writeValue(new File("C:\\Users\\ADMIN\\Documents\\APIGetCallDemo\\src\\main\\java\\com\\qa\\Data\\Users.json"),users);

        //object to json in String
        String usersJsonString=mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

       closeableHttpResponse= postClient.post(url,usersJsonString,headerMap);

       //status code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

        //js0nString
        String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("the response from API is" +responseJson);
        Users userResponseObject=mapper.readValue(responseString,Users.class); //actual users object
        System.out.println(userResponseObject);

        System.out.println(users.getName().equals(userResponseObject.getName()));
        System.out.println(users.getJob().equals(userResponseObject.getJob()));
        System.out.println(users.getId().equals(userResponseObject.getId()));
        System.out.println(users.getCreatedAt().equals(userResponseObject.getCreatedAt()));


    }
}
