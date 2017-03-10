package com.cothing.element.HALApi.deser;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.Map;

public class HALDeserializer extends DelegatingDeserializer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HALDeserializer(BeanDeserializerBase delegatee) {
		super(delegatee);
	}
	
	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		TreeNode  treeNode =  jp.getCodec().readTree(jp) ;
		if(treeNode.isObject()){
			ObjectNode root = (ObjectNode)treeNode;
			for(ReservedProperty rp:ReservedProperty.values()){
				ObjectNode on = (ObjectNode)treeNode.get(rp.getPropertyName());
				if(on !=null){
					Iterator<Map.Entry<String,JsonNode>> it = on.fields();
					while(it.hasNext()){
						Map.Entry<String, JsonNode> jn = it.next();
						root.set(rp.alternateName(jn.getKey()), jn.getValue());
					}
					root.remove(rp.getPropertyName());
				}
			}
		} 
		final JsonParser modifiedParser = treeNode.traverse(jp.getCodec());
		modifiedParser.nextToken();
		return _delegatee.deserialize(modifiedParser, ctxt);
	}

	@Override
	protected JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> newDelegatee) {
		return new HALDeserializer((BeanDeserializerBase) newDelegatee);
	}

}
