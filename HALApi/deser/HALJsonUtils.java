package com.cothing.element.HALApi.deser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HALJsonUtils {

	public static List<Object> decodeJSONObject(JSONObject object,List<Object> list) {

		Object o = null;
		String key;

		Iterator<String> keys = object.keys();
		while (keys.hasNext()) {
			key = keys.next();
			o = object.get(key);

			if (o instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) o;
				
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					decodeJSONObject(obj, list);
				}

			} else if (o instanceof JSONObject) {
				decodeJSONObject((JSONObject) o, list);

			} else {
				list.add(o);
			}

		}

		return list;
	}
	
	public static List decode(JSONObject object,List list) {

		Object o = null;
		String key = null;
		List node = new ArrayList();
		List element = new ArrayList();
		
		Iterator<String> keys = object.keys();
		while (keys.hasNext()) {
			key = keys.next();
			o = object.get(key);

			if (o instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) o;
				
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					decode(obj, list);
				}

			} else if (o instanceof JSONObject) {
				decode((JSONObject) o, list);

			} else {
				element.add(o);
				if(list.size()!= 0){
					for (int i = 0; i < list.size(); i++) {
						List temp = new ArrayList();
						node = (List) list.get(i);
						temp = node;
						node = element;
						element = temp;
						node.add(element);
					}	
				}
				
		}
		}
		
	   list.add(element);
		
		return list;
	}
	public static void main(String[] args) {
		final String str = "{"
//				 + "\"_links\":{"
//				 + "\"child\":[{\"href\":\"/top/1/child/1\"},{\"href\":\"/top/1/child/2\"}],"
//				 + "\"empty:list\":[],"
//				 + "\"null:list\":[],"
//				 + "\"self\":{\"href\":\"/top/self\"},"
//				 + "\"edit\":{\"href\":\"/top/edit\"},"
//				 + "\"templated\":{\"href\":\"/uri/{id}\",\"templated\":true}"
//				 + "},"
				 + "\"_embedded\":{"
				 + "\"emage\":\"emage\","
				 + "\"emname\":\"emname\","
				 + "\"_embedded\":{"
				 + "\"emform\":\"emform\","
				 + "\"emelem\":\"emelem\","
				 + "}"
				 + "},"
//				 + "\"child\":["
//				 + "{\"embed1\":{\"self\":{\"href\":\"/embed1/1/child/1\"}},\"id\":\"id989\"},"
//				 + "{\"embed2\":{\"self\":{\"href\":\"/embed2/1/child/2\"}},\"id\":\"id765\"}"
//				 + "]"
					+ "\"age\":\"12\","
					+ "\"operationType\":\"WAVELET_APPEND_BLIP\","
					+ "\"waveId\":\"12\","
					+ "\"blipId\":\"12\","
					+ "\"author\":\"333@local.net\","
					+ "\"name\":\"xiaoming\"}"
				 + "}," ;
		JSONObject jsonObject = JSONObject.fromObject(str);
//		Object[] array = new Object[str.length()]; 
		List list = new ArrayList();
		List result = HALJsonUtils.decode(jsonObject, list);
		for (int i = 0; i < result.size(); i++) {
			
				System.out.println("list:"+result.get(i));
			
			
			
		}
		System.out.println("list:"+result);
	}
}