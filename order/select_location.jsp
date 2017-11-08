<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.User,com.ojeksimangpred.bean.Driver" %>

<html>
<head>
	<title>Select Location</title>
	<link rel="stylesheet" type="text/css" href="../css/default_style.css">
	<link rel="stylesheet" type="text/css" href="../css/order.css">
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
			<%@include file="../template/header.jsp"%>
		</div>
		<script>
        	document.getElementById("order_link").setAttribute("class", "menu menu_active");
        </script>
		<div class="order_container">
			<div class="subheader">
        		<div class="title"><h1>Make an Order</h1></div>
        	</div>
			<div class="submenu_container">
				<div class="submenu left submenu_active">
					<div class="step_num">
						<p>1</p>
					</div>
					<div class="step_name">
						<p>Select Destination</p>
					</div>
				</div>
			
				<div class="submenu mid">
					<div class="step_num">
						<p>2</p>
					</div>
					<div class="step_name">
						<p>Select a Driver</p>
					</div>
				</div>

				<div class="submenu right">
					<div class="step_num">
						<p>3</p>
					</div>
					<div class="step_name">
						<p>Complete Order</p>
					</div>
				</div>
			</div>


			<!-- <% 
				out.print("select_driver.jsp?user=");
				out.print(json);
				
			%>  -->

			<form method="post" id="submit_select_loc" name="submit_select_loc" action="select_driver.jsp?user="+<%=json %>>
				<div class="content" id="select_destination">
					<div>
						<span class="loc_form_label">Picking point</span>
						<input type="text" name="picking_point" id="picking_point">
					</div>
					<div>
						<span class="loc_form_label">Destination</span>
						<input type="text" name="destination" id="destination">
					</div>
					<div>
						<span class="loc_form_label">Preferred driver</span>
						<input type="text" name="preferred_driver" placeholder="(optional)">
					</div>
					<div>
						<input type="submit" value="Next" class="button green" id="loc_button">
					</div>
				</div>
			</form>
		</div>
		
	</div>
</body>

<script type="text/javascript">
        function validateForm() {
            if(document.submit_select_loc.picking_point.value == null || document.submit_select_loc.picking_point.value == "") {
                window.alert("Please fill the picking location");
                return false;
            } else if (document.submit_select_loc.destination.value == null || document.submit_select_loc.destination.value == "") {
                winddow.alert("Please fill the destination location");
                return false;
            }
        }
	</script>
</html>