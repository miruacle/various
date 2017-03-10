package com.cothing.element.HALApi;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HALMapper extends ObjectMapper{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final JacksonHALModule DEFAULT_HAL_MODULE = new JacksonHALModule();
	
	public HALMapper(){
		registerModule(DEFAULT_HAL_MODULE);
	}
	public HALMapper(HALMapper mapper){
		super(mapper);
		registerModule(DEFAULT_HAL_MODULE);
	}

	
	 @Override
	public ObjectMapper copy(){
		return new HALMapper(this);
	}
}
