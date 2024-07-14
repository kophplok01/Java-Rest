package com.webops.automation.java.testing.config;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class BrowserStackWebDriverHelper {
    public void tearDown(String sessionId, String scenarioName, String scenarioStatus) throws URISyntaxException, IOException {
        URI uri = new URI("https://" + System.getenv("BROWSERSTACK_USERNAME") + ":" + System.getenv("BROWSERSTACK_ACCESS_KEY") + "@api.browserstack.com/automate/sessions/" + sessionId + ".json");
        HttpPut putRequest = new HttpPut(uri);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add((new BasicNameValuePair("status", scenarioStatus)));
        nameValuePairs.add((new BasicNameValuePair("name", scenarioName)));
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpClientBuilder.create().build().execute(putRequest);
    }
}