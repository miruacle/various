package com.cothing.element.HALApi;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.cothing.element.HALApi.resource.HALOperations;

public class GetJsonInterfaceInfo {
	private static final String CHARSET = "utf-8";
	private static String urlPrefix = "192.168.1.18:9898";
	
	
	public String getUrlpath(String servletPath){
		StringBuilder urlPath = new StringBuilder();
		return urlPath.append(urlPrefix).append(servletPath).toString();
	}
	
	public void getJsonInfo(HALOperations halOperations){
		String selfUrl = halOperations.self.getHref();
		String urlPath = getUrlpath(selfUrl);
		HttpURLConnection connection = null;
		String httpQuery = "";
		
//		try {
//			httpQuery = "address=" + URLEncoder.encode(username, CHARSET)
//					+ "&password=" + URLEncoder.encode(password, CHARSET);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

		URL url;
		try {
			url = new URL(urlPath);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Charset", CHARSET);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=" + CHARSET);

			OutputStream out = connection.getOutputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void postJsonInfo(HALOperations halOperations){
		String selfUrl = halOperations.edit.getHref();
		String urlPath = getUrlpath(selfUrl);
		HttpURLConnection connection = null;
		String httpQuery = "";
		
//		try {
//			httpQuery = "address=" + URLEncoder.encode(username, CHARSET)
//					+ "&password=" + URLEncoder.encode(password, CHARSET);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

		URL url;
		try {
			url = new URL(urlPath);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Charset", CHARSET);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=" + CHARSET);

			OutputStream out = connection.getOutputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
