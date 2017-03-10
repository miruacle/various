package com.cothing.element.HALApi.deser;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import com.cothing.element.HALApi.annotation.EmbeddedResource;
import com.cothing.element.HALApi.annotation.Link;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public enum ReservedProperty {
	
LINKS("_links", Link.class), EMBEDDED("_embedded", EmbeddedResource.class);
	 
	 private final String name;
	 private final UUID prefix = UUID.randomUUID();
	 private final Class<? extends Annotation> annotation;
	 private final Method valueMethod;
	
	 
	 private ReservedProperty(String name,
			Class<? extends Annotation> annotation) {
		this.name = name;
		this.annotation = annotation;
		try{
			valueMethod = annotation.getDeclaredMethod("value");
		}catch(NoSuchMethodException e){
			throw new RuntimeException(e);
		}
		
	}


	public String getPropertyName() {
		return name;
	}
	
	public String alternateName(BeanPropertyDefinition bpd,String originalName){
		AnnotatedMember annotatedMember = firstNonNull(bpd.getField(),bpd.getSetter(),bpd.getGetter());
		if(annotatedMember == null){
			return originalName;
		}
		
		Annotation annota = annotatedMember.getAnnotation(annotation);
		if(annota !=null){
			try{
				String alternateName  = (String)valueMethod.invoke(annota);
				return alternateName(alternateName.isEmpty() ? originalName:alternateName);
			}catch(IllegalAccessException|IllegalArgumentException|InvocationTargetException e){
				throw new RuntimeException(e);
			}
		}
		return originalName;
	}
	 
	public String alternateName(String originalName) {
		 return prefix.toString() + ":" + originalName;
		 }


@SafeVarargs
	static <T> T firstNonNull(T... vals){
		for(T v:vals){
			if(v != null){
				return v;
			}
		}
		return null;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
