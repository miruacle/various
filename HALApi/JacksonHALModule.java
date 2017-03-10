package com.cothing.element.HALApi;

import com.cothing.element.HALApi.deser.HALDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonHALModule extends SimpleModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JacksonHALModule(){
		super("JacksonHALModule",PackageVersion.VERSION);
	}

	@Override
	public void setupModule(SetupContext context) {
		context.addBeanDeserializerModifier(new HALDeserializerModifier());
	}
}
