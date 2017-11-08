<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.*,com.ojeksimangpred.OjolServices.OrderManagerInterface" %>
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
    				<a class="history_menu menu_active" href=<?php echo 'transaction_history.php?id='.$user_id; ?>>
						<h3>MY PREVIOUS ORDER</h3>
					</a>
    			</li>
    			<li>
    				<a class="history_menu" href=<?php echo 'driver_history.php?id='.$user_id; ?>>
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
							OrderManagerInterface LM = service.getPort(OrderManagerInterface.class);
							int size = getListOrderCustomer(user.getId());
		                    if(size != 0)
		                    {
		                    		int i = 1;
		                        	for (Order order : getListOrderCustomer(user.getId())) {
                                    if (order.getCustomerVisibility.equals("visible")) {
                                        out.println("<script>var order_date = new Date('"+order.getDate()+"');</script>");
                                        
                                        echo
                                        out.println("<tr>");
                                        out.println("<td class='img_col'>");
                                        out.println("<img class='history_pict'" + "<img class='history_pict'" + src='../IDServices/ImageRetriever?username=<% out.println(user.getUsername()); %>" onerror="this.src='../img/default_profile.jpeg'">
                                        out.println("</td>");
                                        out.println("<td class='order_data'>");
                                            out.println("<div class='left_data'>");
                                            out.println("<p class='history_date' id="+i+"'></p>"
                                            <script>
                                                document.getElementById('row".$i."').innerHTML=format_date(order_date);
                                            </script>
                                            <p class='history_username'>".$driver_name."</p>
                                            <p class='history_loc'>".$row['pick_city']." - ".$row['dest_city']."</p>
                                            <p class='history_rating'>You rated: ";
                                        
                                            for ($i = 0; $i < $row['score']; $i++) {
                                                echo "<span style='color:orange'>&starf;</span>";
                                            }
                                        
                                        echo
                                            "</p>
                                            <p class='history_comment'>You commented:</p>
                                            <p class='history_comment' style='margin-left: 30px;'>".$row['comment']."</p>
                                            </div>
                                            <div class'right_data'>
                                                <form style='display: inline' action='hideHistory.php'>
                                                    <input type='hidden' name='user_id' value='".$user_id."'>
                                                    <input type='hidden' name='order_id' value='".$row['order_id']."'>
													<input type='hidden' name='status' value='".$status."'>
                                                    <input type='submit' class='hide_hist_button' value='HIDE'>
                                                </form>
                                            </div>
                                        </td>
                                        </tr>";
                                        
                                        $i++;
                                    }
		                        }
		                    }
		                %>
					</tbody>
	    		</table>
    		</div>
        </div>
	</div>
</body>
</html>

