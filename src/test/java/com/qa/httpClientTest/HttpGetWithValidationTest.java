package com.qa.httpClientTest;

import com.qa.Util.TestUtil;
import com.qa.base.TestBase;

import com.qa.httpClient.HttpGetWithValidation;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;

public class HttpGetWithValidationTest extends TestBase {

    public HttpGetWithValidationTest() throws FileNotFoundException {
    }
    public  TestBase testBase;
    String url;
    String serviceurl;
    String apiurl;
    HttpGetWithValidation httpGetWithValidation;

    CloseableHttpResponse closeableHttpResponse;





    @BeforeMethod
    public void SetUp() throws FileNotFoundException {
        testBase=new TestBase();
        serviceurl= prop.getProperty("URL");
        apiurl= prop.getProperty("serviceUrl");

        url=serviceurl+apiurl;
       // System.out.println(url);


    }

    @Test
    public void getAPITest() throws IOException {
        httpGetWithValidation=new HttpGetWithValidation();

        HashMap<String,String> headerMap=new HashMap<String,String>();
        headerMap.put("Content-Type", "applicaton/json");
//        headerMap.put("usename", "sonali@123");
//        headerMap.put("password", "test123");
//        headerMap.put("Auth-Token", "12345");
       closeableHttpResponse= httpGetWithValidation.get(url,headerMap);

       //Status code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("status code is --->" +statusCode);

        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200, "status code is not 200");

        //JSOnString
        String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        JSONObject jsonObject=new JSONObject(responseString);
        System.out.println("Response JSON from API is");

        //single value assertion
        //per-page
        String perPageValue= TestUtil.getValueByJPath(jsonObject, "/per_page");
        System.out.println("value of per page is"  +perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 6);

        //total value
        String totalValue=TestUtil.getValueByJPath(jsonObject, "/total");
        System.out.println("value of total is" +totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);

        //get the value from json array

        String Id=TestUtil.getValueByJPath(jsonObject, "/data[0]/id");
        String Email=  TestUtil.getValueByJPath(jsonObject, "/data[1]/email");
        String firstName=TestUtil.getValueByJPath((jsonObject), "/data[2]/first_name");
        String lastName=  TestUtil.getValueByJPath(jsonObject, "/data[3]/last_name");
        String Avatar=TestUtil.getValueByJPath(jsonObject, "/data[4]/avatar");

        System.out.println(Id);
        System.out.println(Email);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(Avatar);


//          Assert.assertEquals(Integer.parseInt(Id),6);
//        Assert.assertEquals(Integer.parseInt(Email),6);
//        Assert.assertEquals(Integer.parseInt(firstName),6);
//        Assert.assertEquals(Integer.parseInt(lastName),6);
//        Assert.assertEquals(Integer.parseInt(Avatar), 6);


        //All Headers
        Header[] headersArray=closeableHttpResponse.getAllHeaders();
        HashMap<String,String> allHeader=new HashMap<String,String>();
        for(Header header:headersArray){
            allHeader.put(header.getName(), header.getValue());
        }
    }
}
