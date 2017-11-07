<%@ page import="javax.servlet.*,javax.servlet.http.*" %>

<html>
<body>
	<% 
		//request.setAttribute("action", "checkAccess");
		//request.getRequestDispatcher("/servlet/AccessManager").forward(request,response);
		response.sendRedirect("login/login.html");
	%>
</body>
</html>
