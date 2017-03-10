package com.cothing.element.HALApi.resource;

import com.cothing.element.HALApi.HALLink;
import com.cothing.element.HALApi.annotation.Link;
import com.cothing.element.HALApi.annotation.Resource;

@Resource
public class ChildResource {
public String id;
@Link
public HALLink self;
}
