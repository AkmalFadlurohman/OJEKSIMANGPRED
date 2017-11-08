<%@ page import="javax.servlet.*,javax.servlet.http.*" %>

<html>
<body>
	<% 
		String uJson = request.getParameter("user");
		User user = new Gson().fromJson(uJson,User.class);
		response.sendRedirect("./select_location.jsp?user="+uJson);
	%>
</body>
</html>