package com.ojeksimangpred.OjolServices;

import javax.jws.WebService;

@WebService(endpointInterface = "com.ojeksimangpred.OjolServices.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
}
