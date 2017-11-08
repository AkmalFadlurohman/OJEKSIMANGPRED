<%@ page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,com.ojeksimangpred.OjolServices.HelloWorld,com.ojeksimangpred.OjolServices.LocationManagerInterface" %>
<html>
<body>
	<% 
		URL url = new URL("http://www.ojeksimangpred.com/OjolServices/aloha?wsdl");
	
		QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "HelloWorldImplService");
	
		Service service = Service.create(url, qname);
	
		HelloWorld hello = service.getPort(HelloWorld.class);
	
		out.println(hello.getHelloWorldAsString("Jekk"));
		
		/*URL url = new URL("	http://www.ojeksimangpred.com/OjolServices/LocationManager?wsdl");
		
		QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "LocationManagerService");
	
		Service service = Service.create(url, qname);
	
		LocationManagerInterface LM = service.getPort(LocationManagerInterface.class);
		
		int driverID = 1;//Integer.parseInt(request.getParameter("driverId"));
		String newLoc = "Cisitu";//request.getParameter("new_location");
		//out.println(driverID);
		//out.println(newLoc);
		LM.addLocation(driverID,newLoc);
		out.println("Test");*/
	%>
</body>
</html>
