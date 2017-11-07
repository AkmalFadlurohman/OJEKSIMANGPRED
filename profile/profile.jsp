<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.User" %>
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
				String json = request.getParameter("user");
				User user = new Gson().fromJson(json,User.class);
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
                <div class="edit_profile_button"><a href='edit_profile.jsp?user=<%out.println(json);%>'>✎</a></div>
            </div>
            <div class="profile_info_container">
                <img class="profile_pict_frame" id="profile_pict" src="../IDServices/ImageRetriever?username=<% out.println(user.getUsername()); %>" onerror="this.src='../img/default_profile.jpeg'">
                <div class="profile_data_container">
                		<div class='username_display'><strong>@<%out.println(user.getUsername()); %></strong></div>
                   	<p><%out.println(user.getFullname()); %></p>
                     <% if (user.getStatus().equals("driver")) {
                            out.println("<p>Driver | <span style='color : #f9880e'>☆<span id='driver_rating'>Rating</span></span> <span id='driver_votes'>(xxx Votes)</span></p>");
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
                		<div class="edit_prefloc_button"><a href="edit_profile.jsp?user='<%out.println(json); %>">✎</a></div>
            		</div>
            		<div class="prefloc_list">
            		</div>
       		</div>    
        </div>
        
	</div>
</body>
</html>
