<!DOCTYPE html>
<html>
<head>
	<title>Select Driver</title>
	<link rel="stylesheet" type="text/css" href="../css/default_style.css">
	<link rel="stylesheet" type="text/css" href="../css/order.css">
	<link rel="stylesheet" type="text/css" href="../css/header.css">
</head>
<body>
	<div class="frame">
		<div class="header">
			<%
			    String uJson = request.getParameter("user");
            	User user = new Gson().fromJson(uJson,User.class);
            	String ppoint = request.getParameter("picking_point");
            	String dest = request.getParameter("destination");
            	String prefdrv = request.getParameter("preffered_driver");

            	URL url = new URL("	http://localhost:9999/OjekSiMangPred/OjolServices/OrderManager?wsdl");
    					
    			QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "OrderManagerService");

    			Service service = Service.create(url, qname);

    			OrderManagerInterface OM = service.getPort(OrderManagerInterface.class);
				%>
				
		</div>
		<div class="menu_container">
			<?php include'../template/menu.php';?>
		</div>
		<script>
        	document.getElementById("order_link").setAttribute("class", "menu menu_active");
        </script>

		<div class="order_container">
			<div class="subheader">
	    		<div class="title"><h1>Make an Order</h1></div>
	    	</div>
			<div class="submenu_container">
				<div class="submenu left">
					<div class="step_num">
						<p>1</p>
					</div>
					<div class="step_name">
						<p>Select Destination</p>
					</div>
				</div>
			
				<div class="submenu mid submenu_active">
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


			<div id="driver_table_container">
				<form method="post" id="submit_select_drv" action=
				<%
					out.print("select_driver.jsp?user=\"");
					out.print(uJson);
					out.print("&");
					out.print("\"picking_point\"=\"");
					out.print(ppoint);
					out.print("\"");
					out.print("&");
					out.print("\"destination\"=\"");
					out.print(dest);
					out.print("\"");
					out.print("&");
					out.print("\"preffered_driver\"=\"");
					out.print(prefdrv);
					out.print("\"");
				%>>
					<div class="content" id="select_driver">
						<div id="preferred_driver">
							<h2>Preferred driver</h2>
							<%
								if(prefdrv == "") {
									int [] hasil;
									int [] iddriver;
									int i = 1;
									hasil = OM.getPreferedLocation(ppoint);
									
									for (i <= hasil[1]) {
									iddriver[i-1]=hasil[i];
									out.println("<table class='driver_table'>");
									out.println("<colgroup>");
									out.println("<col style='width: 20%;'>");
									out.println("<col style='width: 80%;'>");
									out.println("</colgroup>");
									out.println("<tr>");
									out.println("<td>");
									out.println("</td>");
									out.println("<td class='driver_column'>");
									out.print("<p class='driver_username'>");
									out.print(iddriver[i-1]);
									out.println("</p>");
									out.print("<div class='choose_driver green' onclick='chooseDriver");
									out.print("(");
									out.print(iddrive[i-1]);
									out.println(")'>");
									out.println("I CHOOSE YOU");	
									out.println("</div>");
									out.println("</td>");
									out.println("</tr>");
									out.println("</table>");
									i++;
									}
								} else {
									out.println("<h3>Nothing to display :(</h3>");
								}
							%>
						</div>
						<div id="other_driver">
							<h2>Other drivers</h2>
						</div>
						<input type="hidden" name="picking_point" value=<% out.print(ppoint) %>>
						<input type="hidden" name="destination" value=<% out.print(dest)%>>
						<input type="hidden" name="selected_driver" id="selected_driver">
					</div>
				</form>
			</div>
		</div>
</body>
<script type="text/javascript">
	function chooseDriver(driver_id) {
		document.getElementById('selected_driver').value = driver_id;
		var form = document.getElementById('submit_select_drv');
		form.submit();
	}
</script>
</html>
