<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.*,com.ojeksimangpred.OjolServices.LocationManagerInterface" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="../css/default_style.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
</head>
<body>
    <div class="frame">
        <div class="header">
            <%  
				String uJson = request.getParameter("user");
				User user = new Gson().fromJson(uJson,User.class);
				String dJson = null;
				Driver driver = new Driver();
				if (user.getStatus().equals("driver")) {
					dJson = request.getParameter("driver");
					driver = new Gson().fromJson(dJson,Driver.class);
				}
			%>
			<%@include file="../template/header.jsp"%>
        </div>
        <div class="menu_container">
            <%@include file="../template/menu.jsp"%>
            <script>
                document.getElementById("profile_link").setAttribute("class", "menu menu_active");
            </script>
        </div>
        <div class="profile_container">
            <div class="subheader">
                <div class="title"><h1>My Profile</h1></div>
                <div class="edit_profile_button"><a href='edit_profile.jsp?user=<%out.println(uJson);%>&driver=<%out.println(dJson);%>'>✎</a></div>
            </div>
            <div class="profile_info_container">
                <img class="profile_pict_frame" id="profile_pict" src="../IDServices/ImageRetriever?username=<% out.println(user.getUsername()); %>" onerror="this.src='../img/default_profile.jpeg'">
                <div class="profile_data_container">
                		<div class='username_display'><strong>@<%out.println(user.getUsername()); %></strong></div>
                   	<p><%out.println(user.getFullname()); %></p>
                     <% if (user.getStatus().equals("driver")) {
                            out.println("<p>Driver | <span style='color : #f9880e'>☆<span id='driver_rating'>Rating</span></span> (<span id='driver_votes'>(xxx Votes)</span> votes)</p>");
                            float rating = 0;
                            if (driver.getVotes() != 0) {
                            		rating = driver.getRating();
                            }
                            out.println("<script>document.getElementById('driver_rating').innerHTML ="+rating+";</script>");
                            out.println("<script>document.getElementById('driver_votes').innerHTML ="+driver.getVotes()+";</script>");
                        } else {
                           	out.println("<p>Non-Driver</p>");
                        }
                     %>
                     <p>✉<% out.println(user.getEmail()); %></p>
                     <p>☏<% out.println(user.getPhone()); %></p>
                </div>
            	</div>
            	<div id="display_prefloc" class="prefloc_container">
            		<% 	if (!user.getStatus().equals("driver")) {
                    		out.println("<script>document.getElementById('display_prefloc').style.display = 'none';</script>");
                		}
           		%>
            		<div class="subheader">
                		<div class="title"><h1>Preferred Locations</h1></div>
                		<div class="edit_prefloc_button"><a href='edit_location.jsp?user=<%out.println(uJson);%>&driver=<%out.println(dJson);%>'>✎</a></div>
            		</div>
            		<div class="prefloc_list">
            			<%	
            				URL url = new URL("	http://www.ojeksimangpred.com/OjolServices/LocationManager?wsdl");
    					
    						QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "LocationManagerService");
    				
    						Service service = Service.create(url, qname);
    						LocationManagerInterface LM = service.getPort(LocationManagerInterface.class);
            				if (dJson != null) {
            					int size = LM.retrieveLocation(driver.getId()).length;
            					int bound = size;
            					if (bound >= 3) {
            						bound = 3;
            					}
            					StringBuilder builder = new StringBuilder();
            					builder.append("<ul>");
            					for (int i=0;i<bound;i++) {
            						if (i != bound-1) {
            							builder.append("<li>►"+LM.retrieveLocation(driver.getId())[i]+"</li><ul>");
            						} else {	
            							builder.append("<li>►"+LM.retrieveLocation(driver.getId())[i]+"</li>");
            						}
            					}
            					for (int i=0;i<=bound;i++) {
            						builder.append("</ul>");
            					}
            					out.println(builder.toString());
            				}	
            			%>
            		</div>
       		</div>    
        </div>
        
	</div>
</body>
</html>
