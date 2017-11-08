<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.*,com.ojeksimangpred.OjolServices.LocationManagerInterface" %>

<html>
<head>
<title>Edit Location</title>
    <link rel="stylesheet" type="text/css" href="../css/default_style.css">
    <link rel="stylesheet" type="text/css" href="../css/location.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
</head>
<body>
    <script>
        function showEdit(editID,saveID,locID,dummylocID,currentlocID,formID,deleteID,cancelID) {
            showSave(editID,saveID);
            showCancel(deleteID,cancelID);
            document.getElementById(locID).style.display = "none";
            document.getElementById(dummylocID).value = document.getElementById(locID).innerHTML;
            document.getElementById(currentlocID).value = document.getElementById(locID).innerHTML;
            document.getElementById(formID).style.display = "block";
        }
        function showSave(editID,saveID) {
            document.getElementById(editID).style.display = "none";
            document.getElementById(saveID).style.display = "block";
        }
        function showCancel(deleteID,cancelID) {
            document.getElementById(deleteID).style.display = "none";
            document.getElementById(cancelID).style.display = "block";
        }
        function copyDummytoNewLoc(dummylocID,newlocID) {
            var temp = document.getElementById(dummylocID).value;
            document.getElementById(newlocID).value = temp;
        }
        function hideEdit(editID,saveID,locID,formID,deleteID,cancelID) {
            document.getElementById(editID).style.display = "block";
            document.getElementById(saveID).style.display = "none";
            document.getElementById(locID).style.display = "block";
            document.getElementById(formID).style.display = "none";
            document.getElementById(deleteID).style.display = "block";
            document.getElementById(cancelID).style.display = "none";
        }
        function validateAddLoc(docID) {
            var loc = document.getElementById(docID).value;
            if (loc == null || loc == "") {
                window.alert("Location can't be blank");
                return false;
            }
        }
        function confirmDelete(url) {
            var retVal = confirm("Are you sure you want to delete this preferred location?");
            if (retVal == true) {
                window.open(url,"_self");
            }
        }
    </script>
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
				URL url = new URL("	http://www.ojeksimangpred.com/OjolServices/LocationManager?wsdl");
				
				QName qname = new QName("http://OjolServices.ojeksimangpred.com/", "LocationManagerService");
		
				Service service = Service.create(url, qname);
				LocationManagerInterface LM = service.getPort(LocationManagerInterface.class);
			%>
			<%@include file="../template/header.jsp"%>
        </div>
    <div class="menu_container">
        <%@include file="../template/menu.jsp"%>
        <script>
	        document.getElementById("profile_link").setAttribute("class", "menu menu_active");
	   </script>
    </div>
    <div class="editloc_container">
        <div class="subheader">
            <div class="title"><h1>Edit Preferred Location</h1></div>
        </div>
        <div class="display_loc_frame">
            <table>
                <tr>
                    <th>No</th>
                    <th>Locations</th>
                    <th>Actions</th>
                </tr>
                <%
                		if (dJson != null) {
						int size = LM.retrieveLocation(driver.getId()).length;
						String[] locations = new String[size];
						for (int i=0;i<size;i++) {
							locations[i] = LM.retrieveLocation(driver.getId())[i];
						}
						for (int i=0;i<size;i++) {
							out.println("<tr>");
                            	out.println("<td>"+(i+1)+"</td>");
                            	out.println("<td>");
                                out.println("<div id='prefloc"+(i+1)+"'>"+locations[i]+"</div>");
                                out.println("<div id='form_prefloc"+(i+1)+"' style='display: none'>");
                                    out.println("<input type='text' style=' height: 100%, width: 100%;' id='dummy_prefloc"+(i+1)+"' onkeyup=\"copyDummytoNewLoc('dummy_prefloc"+(i+1)+"','new_prefloc"+(i+1)+"');\">");
                                out.println("</div>");
                            	out.println("</td>");
                            	out.println("<td>");
                                out.println("<div class='edit_operation'>");
                                    out.println("<div class='edit_button' id='edit_prefloc"+(i+1)+"' onClick=\"showEdit('edit_prefloc"+(i+1)+"','save_prefloc"+(i+1)+"','prefloc"+(i+1)+"','dummy_prefloc"+(i+1)+"','current_prefloc"+(i+1)+"','form_prefloc"+(i+1)+"','delete_prefloc"+(i+1)+"','cancel_edit"+(i+1)+"');\">✎</div>");
                                    out.println("<div id='save_prefloc"+(i+1)+"' style='display: none'>");
                                        out.println("<form name='edit_prefloc_form' method='POST' action='../IDServices/AccessManager' style='display: inline;' onsubmit=\"return validateAddLoc('dummy_prefloc"+(i+1)+"');\">");
                                            out.println("<input class='save_button' type='submit' value='Save'>");
                                            out.println("<input type='hidden' name='current_prefloc' id='current_prefloc"+(i+1)+"'>");
                                            out.println("<input type='hidden' name='new_prefloc' id='new_prefloc"+(i+1)+"'>");
                                            out.println("<input type='hidden' name='username' value='"+user.getUsername()+"'>");
                                            out.println("<input type='hidden' name='driverId' value='"+driver.getId()+"'>");
                                            out.println("<input type='hidden' name='action' value='updateLocation'>");
                                        out.println("</form>");
                                    out.println("</div>");
                                    out.println("<div class='delete_button' id='delete_prefloc"+(i+1)+"'>");
                                    		out.println("<form name='delete_prefloc_form' method='POST' action='../IDServices/AccessManager' style='display: inline;'>");
                                    		out.println("<input type='hidden' name='username' value='"+user.getUsername()+"'>");
                                    		out.println("<input type='hidden' name='driverId' value='"+driver.getId()+"'>");
                                    		out.println("<input type='hidden' name='delPrefLoc' value='"+locations[i]+"'>");
                                    		out.println("<input type='hidden' name='action' value='deleteLocation'>");
                                    		out.println("<input type='submit' value='✖'>");
                                    out.println("</div>");
                                    out.println("<div class='cancel_button' id='cancel_edit"+(i+1)+"' style='display: none;' onClick=\"hideEdit('edit_prefloc"+(i+1)+"','save_prefloc"+(i+1)+"','prefloc"+(i+1)+"','form_prefloc"+(i+1)+"','delete_prefloc"+(i+1)+"','cancel_edit"+(i+1)+"');\">Cancel</div>");
                                out.println("</div>");
                     	   	out.println("</td>");
                        		out.println("</tr>");
						}
					}
                %>
            </table>

        </div>
        <div class="add_loc_frame">
            <h2>Add New Location</h2>
            <form name="add_location" action="../IDServices/AccessManager" method="POST">
                <input type="text" id="add_newloc" name="new_location">
                <input type="hidden" name="action" value="addLocation">
                <input type="hidden" name="username" value=<%out.println(user.getUsername());%>>
                <input type="hidden" name="driverId" value=<%out.println(driver.getId());%>>
                <input type="submit" value="ADD" class="button green add">
            </form>
        </div>
        <%	
        		if ("driver".equals(user.getStatus())) {
        			out.println("<a href='profile.jsp?user="+uJson+"&driver="+dJson+"'><div class='button red back'>BACK</div></a>");
        		} else {
        			out.println("<a href='profile.jsp?user="+uJson+"'><div class='button red back'>BACK</div></a>)");
        		}
        %>
    </div>
</body>
</html>
