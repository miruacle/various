package com.cothing.element.HALApi.deser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cothing.element.HALApi.annotation.Resource;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class HALDeserializerModifier extends BeanDeserializerModifier{

	@Override
	public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config,BeanDescription beanDesc, JsonDeserializer<?> deserializer){
		Resource ann = beanDesc.getClassAnnotations().get(Resource.class);
		if(ann != null){
			return new HALDeserializer((BeanDeserializer) deserializer);
		}
		return deserializer;
	}
	@Override
	public List<BeanPropertyDefinition> updateProperties(DeserializationConfig config, BeanDescription beanDesc, List<BeanPropertyDefinition> propDefs) {
		Resource ann = beanDesc.getClassAnnotations().get(Resource.class);
		if(ann != null){
			List<BeanPropertyDefinition> modified = new ArrayList<>();
			Iterator<BeanPropertyDefinition> defIt = propDefs.iterator();
			while(defIt.hasNext()){
				BeanPropertyDefinition pbd = defIt.next();
				for(ReservedProperty rp : ReservedProperty.values()){
					 String alternateName = rp.alternateName(pbd, pbd.getName());
					 if (!pbd.getName().equals(alternateName)) {
					 modified.add(pbd.withName(new PropertyName(alternateName)));
					 defIt.remove();
				}
			}
				
		}
			propDefs.addAll(modified);
		
	}
		return propDefs;
}
}