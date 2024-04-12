package com.qa.httpClient;

import com.qa.base.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpGetWithValidation extends TestBase {

    //CloseableHttpClient closeableHttpClient;
    CloseableHttpResponse closeableHttpResponse;

    public HttpGetWithValidation() throws FileNotFoundException {
    }

    public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        for (Map.Entry<String,String> entry:headerMap.entrySet()){
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
         closeableHttpResponse = closeableHttpClient.execute(httpGet);
        return closeableHttpResponse;

    }

}
