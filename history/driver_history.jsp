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
                    <a class="history_menu" href=<%out.print("'transaction_history.jsp?id=" + user_id + "'"); %> >
                        <h3>MY PREVIOUS ORDER</h3>
                    </a>
                </li>
                <li>
                    <a class="history_menu menu_active" href=<%out.print("'driver_history.jsp?id=" + user_id + "'"); %> >
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
                        <?php
                            $query_order=mysqli_query($con,"SELECT * FROM `order` WHERE `driver_id`='".$user_id."'") or die(mysqli_error($con));

                            if(mysqli_num_rows($query_order)!=0)
                            {
                                $i = 1;
                                while($row=mysqli_fetch_assoc($query_order)) {
                                    $driver_query=mysqli_query($con,"SELECT username FROM user WHERE user_id='".$row['cust_id']."'") or die(mysqli_error());
                                    $driver_row=mysqli_fetch_assoc($driver_query);
                                    $driver_name=$driver_row['username'];
                                    $given_score=(int)$row['score'];
                                    
                                    if ($row['driver_visibility'] == 'visible') {
                                        echo
                                        '<script>
                                        var order_date = new Date("'.$row['date'].'");
                                        </script>';
                                        
                                        echo
                                        "<tr>
                                        <td class='img_col'>
                                        <img class='history_pict' src='../profile/getProfilePict.php?id=".$row['cust_id']."'>
                                        </td>
                                        <td class='order_data'>
                                        <div class='left_data'>
                                        <p class='history_date' id='row".$i."'></p>
                                        <script>
                                        document.getElementById('row".$i."').innerHTML=format_date(order_date);
                                        </script>
                                        <p class='history_username'>".$driver_name."</p>
                                        <p class='history_loc'>".$row['pick_city']." - ".$row['dest_city']."</p>
                                        <p class='history_rating'>gave <span class='yellow_score'>&nbsp&nbsp".$given_score."&nbsp</span> stars for this order</p>
                                            <p class='history_comment'>and left comment:</p>
                                            <p class='history_comment' style='margin-left: 30px;'>".$row['comment']."</p>
                                            </div>
                                            
                                            <div class'right_data'>
                                            <form style='display: inline' method='get' action='hideHistory.php'>
                                            <input type='hidden' name='user_id' value='".$user_id."'>
                                            <input type='hidden' name='status' value='".$status."'>
                                            <input type='hidden' name='order_id' value='".$row['order_id']."'>
                                            <input type='submit' class='hide_hist_button' value='HIDE'>
                                            </form>
                                            </div>
                                            </td>
                                            </tr>";
                                    }

                                        $i++;
                                }
                            }
                            mysqli_close($con);
                        ?>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="hide_history.js"></script>
</body>
</html>
