<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service,javax.servlet.*,javax.servlet.http.*,com.google.gson.Gson,com.ojeksimangpred.bean.*" %>

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
            var temp = document.getElementById(locID).innerHTML;
            document.getElementById(locID).style.display = "none";
            document.getElementById(dummylocID).value = temp;
            document.getElementById(currentlocID).value = temp;
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
            </table>

        </div>
        <div class="add_loc_frame">
            <h2>Add New Location</h2>
            <form name="add_location" action="../IDServices/AccessManager" method="POST">
                <input type="text" id="add_newloc" name="new_location">
                <input type="hidden" name="action" value="addLocation">
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
