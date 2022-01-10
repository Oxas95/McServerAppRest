package com.watolua.mcserverapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public interface MinecraftAuth {

	default String requestMinecraftId(String username, String code) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost("https://api.minecraft.id/gateway/verify/" + username);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("code", code));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    
		    System.out.println(entity2.getContent().toString());
		    
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}
		
		return generateToken();
	}
	
	default String generateToken() {
		Random r = new Random();
		BigDecimal token = new BigDecimal(r.nextLong());
		for(int i = 0; i < 100; i++) {
			token = token.add(new BigDecimal(r.nextLong()));
		}
		
		return String.format("%A", token);
	}
	
}
