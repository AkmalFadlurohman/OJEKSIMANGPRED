package com.ojeksimangpred.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.ojeksimangpred.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
}
