<%@ page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,com.ojeksimangpred.ws.HelloWorld" %>
<html>
<body>
	<% 
		URL url = new URL("http://www.ojeksimangpred.com/OjolServices/aloha?wsdl");
	
		QName qname = new QName("http://ws.ojeksimangpred.com/", "HelloWorldImplService");
	
		Service service = Service.create(url, qname);
	
		HelloWorld hello = service.getPort(HelloWorld.class);
	
		out.println(hello.getHelloWorldAsString("Jekk")); 
	%>
</body>
</html>
