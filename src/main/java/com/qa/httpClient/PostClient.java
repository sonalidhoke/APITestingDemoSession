package com.qa.httpClient;


import com.qa.base.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.HashMap;
import java.util.Map;

public class PostClient extends TestBase {

    CloseableHttpResponse closeableHttpResponse;

    public PostClient() throws FileNotFoundException {
    }

    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpPost  httpPost=new HttpPost(url);
        httpPost.setEntity(new StringEntity(entityString)); //for payload

        //for headers
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        closeableHttpResponse = httpClient.execute(httpPost);

        
       return closeableHttpResponse;
    }
}
