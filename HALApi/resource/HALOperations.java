package com.cothing.element.HALApi.resource;

import java.util.Collection;

import com.cothing.element.HALApi.HALLink;
import com.cothing.element.HALApi.annotation.EmbeddedResource;
import com.cothing.element.HALApi.annotation.Link;
import com.cothing.element.HALApi.annotation.Resource;
import com.google.wave.api.OperationType;

@Resource
public  class HALOperations {
	 
public String id;
public String name;
public String age;

public OperationType operationType;
public String waveId;
public String blipId;
public String author;

@Link
public HALLink self;
@Link
public HALLink edit;
@Link
public HALLink templated;
@Link
public HALLink nullLink;
@Link("child")
public Collection<HALLink> childLinks;
@Link("empty:list")
public Collection<HALLink> childLinksEmpty;
@Link("null:list")
public Collection<HALLink> childLinksNull;
@EmbeddedResource("child")
public Collection<ChildResource> children;
@EmbeddedResource
public String nullString;
}