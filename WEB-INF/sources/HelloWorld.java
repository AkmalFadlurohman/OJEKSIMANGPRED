package com.ojeksimangpred.OjolServices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)

public interface HelloWorld{

	@WebMethod String getHelloWorldAsString(String name);

}
