<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.*,com.ojeksimangpred.OjolServices.OrderManagerInterface,com.ojeksimangpred.IDServices.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>Previous Order History</title>
	<link rel="stylesheet" type="text/css" href="../css/default_style.css">
    <link rel="stylesheet" type="text/css" href="../css/history.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">

	<script type="text/javascript" src="format_date.js"></script>
    </script>
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
            	document.getElementById("history_link").setAttribute("class", "menu menu_active");
            </script>
        </div>
        <div class="history_container">
        	<div class="subheader">
        		<div class="title"><h1>Transaction History</h1></div>
        	</div>
    		<ul id="history_nav" class="nav_bar">
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
						<%
							URL url = new URL("	http://www.ojeksimangpred.com/OjolServices/OrderManager?wsdl");
    					
							QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "OrderManagerService");
				
							Service service = Service.create(url, qname);
							OrderManagerInterface OM = service.getPort(OrderManagerInterface.class);
							OM.getListOrderDriver(user.getId());
							int size = OM.getLength();
							//out.println(size);
		                   	 if (size != 0)
		                   	 {
		                   	 	out.println(OM.getOrderI(0).getPickLoc());
		                        	/*for (int i = 1; i<=size; i++ ) {
		                        		Order order = new Order();
		                        		order = OM.getOrderI(i-1);
		                        		out.println("test");
		                        		User d = new User();
		                        		d = UserManager.getUser(order.getDriverId());
                                    	if ("visible".equals(order.getCustomerVisibility())) {
                                        out.println("<script>var order_date = new Date('"+order.getDate()+"');</script>");
                                        out.println("<tr>");
                                        out.println("<td class='img_col'>");
                                        out.println("<img class='history_pict'" + "<img class='history_pict'" + "src='"+"../IDServices/ImageRetriever?username="+user.getUsername()+" onerror=this.src='../img/default_profile.jpeg'>");
                                        out.println("</td>");
                                        out.println("<td class='order_data'>");
                                            	out.println("<div class='left_data'>");
                                            	out.println("<p class='history_date' id='row"+i+"'></p>");
                                            	out.println("<script>document.getElementById('row"+i+"').innerHTML=format_date(order_date);</script>");
                                            	out.println("<p class='history_username'>"+d.getFullname()+"</p>");
                                            	out.println("<p class='history_loc'>"+order.getPickLoc()+" - "+order.getDestLoc()+"</p>");
                                            	out.println("<p class='history_rating'>You rated: ");
                                        		for (int j = 0; j < order.getScore(); j++) {
                                                out.println("<span style='color:orange'>&starf;</span>");
                                            	}
                                            out.println("</p>");
                                            out.println("<p class='history_comment'>You commented:</p>");
                                            out.println("<p class='history_comment' style='margin-left: 30px;'>"+order.getComment()+"</p>");
                                            out.println("</div>");
                                            out.println("<div class'right_data'>");
                                                out.println("<form style='display: inline' action='hideHistory.php'>");
                                                    	out.println("<input type='hidden' name='user_id' value='"+user.getId()+"'>");
                                                    	out.println("<input type='hidden' name='order_id' value='"+order.getOrderId()+"'>");
													out.println("<input type='hidden' name='status' value='"+d.getStatus()+"'>");
                                                    out.println("<input type='submit' class='hide_hist_button' value='HIDE'>");
                                                out.println("</form>");
                                            out.println("</div>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                       i++;
                                    }
		                        }*/
		                    }
		                %>
					</tbody>
	    		</table>
    		</div>
        </div>
	</div>
</body>
</html>

