<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.*,com.ojeksimangpred.OjolServices.LocationManagerInterface" %>
<html>
<head>
    <title>Driver History</title>
    <link rel="stylesheet" type="text/css" href="../css/default_style.css">
    <link rel="stylesheet" type="text/css" href="../css/history.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    
    <script type="text/javascript" src="format_date.js"></script>
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
            <script type="text/javascript">
                document.getElementById("history_link").setAttribute("class", "menu menu_active");
            </script>
        </div>
        <div class="history_container">
            <div class="subheader">
                <div class="title"><h1>Transaction History</h1></div>
            </div>

            <ul class="nav_bar" id="history_nav">
              	 <li>
    				<a class="history_menu menu_active" href='transaction_history.jsp?user=<% if (dJson == null)  {out.println(uJson);} else {out.println(uJson+"&driver="+dJson);}%>'>
						<h3>MY PREVIOUS ORDER</h3>
				</a>
    				</li>
    				<li>
    				<a class="history_menu" href='driver_history.jsp?user=<% if (dJson == null)  {out.println(uJson);} else {out.println(uJson+"&driver="+dJson);}%>'>
						<h3>DRIVER HISTORY</h3>
				</a>
    				</li>
            </ul>
            
            <div id="history_table_container">
                <table class="history_table">
                    <colgroup>
                        <col style="width: 20%;">
                        <col style="width: 80%;">
                    </colgroup>

                    <tbody>
                       
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="hide_history.js"></script>
</body>
</html>
