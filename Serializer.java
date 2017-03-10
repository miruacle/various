package com.cothing.element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.inject.spi.Element;
import com.google.wave.api.ElementType;
import com.google.wave.api.FormElement;
import com.google.wave.api.impl.ElementGsonAdaptor;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HALSerializer {

	private Gson gson;
	private JsonParser parser;
	private boolean isRoot = true;
	private List<FormElement> elements;
	
	public HALSerializer() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		this.parser = new JsonParser();
		elements = new ArrayList<FormElement>();
		
	}

	public JsonObject deserialize(String json){
		return parser.parse(json).getAsJsonObject();
	}
	
	public FormElement deserialize(JsonObject object) {
		String defaultValue = "root";
		
		FormElement element = new FormElement(ElementType.STATE);
				
		for (Entry<String, JsonElement> entry : object.entrySet()) {
			
			if (entry.getValue().isJsonPrimitive()) {
				element.setName(entry.getKey());
				element.setValue(entry.getValue().getAsString());
				if(isRoot){
					element.setDefaultValue(defaultValue);
				} else {
					element.setDefaultValue(entry.getKey());
				}
				System.out.println(element.getProperties().toString());
			}

			if (entry.getValue().isJsonObject()) {
				isRoot = false;
				element = deserialize(entry.getValue().getAsJsonObject());
				System.out.println(element.getProperties().toString());
			}
			
			if (entry.getValue().isJsonArray()) {
				for(JsonElement jsonElement : entry.getValue().getAsJsonArray()) {
					//System.out.println(jsonElement.getAsString());
				}
			}
		}
		return element;
	}

	public FormElement dealWithJsonPrimitive(String level, String property, String value){
		FormElement element = new FormElement(ElementType.STATE);
		element.setDefaultValue(level);
		element.setName(property);
		element.setValue(value);
		return element;
	}
	
//	public FormElement deserializeJsonObject(JsonObject object){
//		FormElement element = new FormElement(ElementType.STATE);
//		element.setDefaultValue(level);
//		deserialize(object.toString());
//		return element;
//	}
	
	public String createJSON() {
		JsonObject object = new JsonObject(); // 创建一个json对象

		//JsonObject links = new JsonObject();
		//links.addProperty("self", "href");
		//object.add("_links", links);

		JsonObject embedded = new JsonObject();
		JsonObject logo = new JsonObject();
		JsonObject link = new JsonObject();
		JsonObject img = new JsonObject();
		img.addProperty("href", "/logo");
		img.addProperty("title", "酷寻");
		link.add("img", img);
		logo.add("_links", link);
		embedded.add("logo", logo);
		object.add("_embedded", embedded);

		object.addProperty("name", "酷寻信息科技"); // 为json对象添加属性
		object.addProperty("legalName", "上海酷寻信息科技有限公司");

		JsonArray telephone = new JsonArray(); // 创建json数组
		JsonPrimitive number = new JsonPrimitive("0212345678");
		telephone.add(number); // 将json primitive添加到数组
		number = new JsonPrimitive("0123456789");
		telephone.add(number);

		object.add("telephone", telephone); // 将数组添加到json对象
		object.addProperty("code", "12345678901");
		// gson.toJson(Pojo.java);
		return gson.toJson(object);
	}

	public static void main(String[] args) {

		HALSerializer serializer = new HALSerializer();
		String json = serializer.createJSON();
		System.out.println(json);
		JsonObject object = serializer.deserialize(json);
		
		serializer.deserialize(object);
	}
}

