package com.qa.httpClient;

import com.qa.base.TestBase;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class GetClient extends TestBase {

    public GetClient() throws FileNotFoundException {
    }

    public void get(String url) throws IOException {
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();

       //String  url="https://reqres.in//api/users";
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);

        int StatusCode= closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code-----?" +StatusCode);

        String resposeString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJSON=   new JSONObject(resposeString);
        System.out.println("ResponseJson From API-->" +responseJSON);

        Header[] headersArray=closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders= new HashMap<String,String>();
        for (Header header:headersArray){
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers array-->  " +allHeaders);
    }
}
