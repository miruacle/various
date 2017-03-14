package com.cothing.element;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.cothing.element.node.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class HALAdaptor implements JsonDeserializer<JsonNode> {

	//private static final String EMBEDDED_ROOT = "_embedded";
	private static final String LINK_ROOT = "_links";
	
	@Override
	public JsonNode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	        throws JsonParseException {
		JsonNode node = new JsonNode();
		JsonObject object = json.getAsJsonObject();
		Iterator<Entry<String, JsonElement>> iterator = object.entrySet().iterator();
		while(iterator.hasNext()){
			node.setAttributes(iterator.next().getKey(), iterator.next().getValue().getAsString());
		}
		return null;
	}

	public JsonNode deserialize(String json, JsonNode node){
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();
		//JsonNode node = new JsonNode();
		for (Entry<String, JsonElement> entry : object.entrySet()) {
			if (entry.getValue().isJsonObject()) {
				if (entry.getKey().equals(LINK_ROOT)) {
					System.out.println("drop this object.");
				} else {
					JsonNode subNode = new JsonNode();
					subNode.setAttributes(entry.getKey(), entry.getValue().toString());
					node.setChild(subNode);
					deserialize(entry.getValue().toString(), subNode);
				}
			}

			if (entry.getValue().isJsonPrimitive()) {
				JsonNode subNode = new JsonNode();
				subNode.setAttributes(entry.getKey(), entry.getValue().getAsString());
				node.setChild(subNode);
			}

			if (entry.getValue().isJsonArray()) {
				//System.out.println("deal with json array: " + entry.getValue());
			}
		}
		return node;
	}
	
	public static void main(String[] args) {
		JsonObject object = new JsonObject();
		JsonObject subObject = new JsonObject();
		subObject.addProperty("subName", "subValue");
		object.addProperty("name", "value");
		object.add("subObject", subObject);
		String json = object.toString();
		//System.out.println(json);
		
		Map<String, String> test = new HashMap<String, String>();
		test.put("address", "testAddress");
		test.put("name", "testName");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String simpleJson = gson.toJson(test);
		
		JsonNode node = new JsonNode();
		JsonNode rootNode = new HALAdaptor().deserialize(simpleJson, node);
		Set<JsonNode> children = rootNode.getChildren();
		for(JsonNode child : children){
			child.setParent(rootNode);
			System.out.println(child.getAttributes().toString());
			if(child.getChildren() != null){
				Iterator<JsonNode> it = child.getChildren().iterator();
				while(it.hasNext()){
					System.out.println(it.next().getAttributes().toString());
				}
			}
		}
	}
}
