<!DOCTYPE html>
<html>
<head>
	<title>Complete Your Order</title>
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
            	String seldrv = request.getParameter("selected_driver");




			%>

		</div>
		<div class="menu_container">
			<% include'../template/menu.jsp'; %>
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
			
				<div class="submenu mid">
					<div class="step_num">
						<p>2</p>
					</div>
					<div class="step_name">
						<p>Select a Driver</p>
					</div>
				</div>

				<div class="submenu right submenu_active">
					<div class="step_num">
						<p>3</p>
					</div>
					<div class="step_name">
						<p>Complete Order</p>
					</div>
				</div>
			</div>


			<form id="submit_cmplt_ordr" method="post" action="order_handler.jsp">
				<div class="content" id="complete_order">
					<h2>How was it?</h2>
					<div id="driver_profile">				
					</div>
					<div class="rating_bar" style="background-color: rgba(0,255,0,0.2);">
						<span class="star" id="1-star" onclick="rate1()">&starf;</span>
						<span class="star" id="2-star" onclick="rate2()">&starf;</span>
						<span class="star" id="3-star" onclick="rate3()">&starf;</span>
						<span class="star" id="4-star" onclick="rate4()">&starf;</span>
						<span class="star" id="5-star" onclick="rate5()">&starf;</span>
						<input type="hidden" name="rating" id="rating">
					</div>
					<textarea id="comment" name="comment" form="submit_cmplt_ordr" rows="8" cols="35" placeholder="Your comment..."></textarea>
					<input type="hidden" name="picking_point" value=<% out.print(ppoint) %>>
					<input type="hidden" name="destination" value=<% out.print(dest) %>>
					<input type="hidden" name="selected_driver" value=<% out.print(seldrv) %>> 
					<input type="hidden" name="customer" value=<% out.print(user_id) %>>

					<div id="finish_button_container">
						<input id="finish_button" class="button green" type="submit" name="submit" value="Complete Order">
					</div>

				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	var star1 = document.getElementById('1-star');
	var star2 = document.getElementById('2-star');
	var star3 = document.getElementById('3-star');
	var star4 = document.getElementById('4-star');
	var	star5 = document.getElementById('5-star');
	var rate = document.getElementById('rating');

	rate3();

	function rate1() {
		rate.value = 1;
		light1();
	}	
	function rate2() {
		rate.value = 2;
		light2();
	}
	function rate3() {
		rate.value = 3;
		light3();
	}
	function rate4() {
		rate.value = 4;
		light4();
	}
	function rate5() {
		rate.value = 5;
		light5();
	}	

	function light1() {
		rate.value = 1;
		star1.style.color = "yellow";
		star2.style.color = "gray";
		star3.style.color = "gray";
		star4.style.color = "gray";
		star5.style.color = "gray";
	}
	function light2() {
		rate.value = 2;
		star1.style.color = "yellow";
		star2.style.color = "yellow";
		star3.style.color = "gray";
		star4.style.color = "gray";
		star5.style.color = "gray";
	}
	function light3() {
		rate.value = 3;
		star1.style.color = "yellow";
		star2.style.color = "yellow";
		star3.style.color = "yellow";
		star4.style.color = "gray";
		star5.style.color = "gray";
	}
	function light4() {
		rate.value = 4;
		star1.style.color = "yellow";
		star2.style.color = "yellow";
		star3.style.color = "yellow";
		star4.style.color = "yellow";
		star5.style.color = "gray";
	}
	function light5() {
		rate.value = 5;
		star1.style.color = "yellow";
		star2.style.color = "yellow";
		star3.style.color = "yellow";
		star4.style.color = "yellow";
		star5.style.color = "yellow";
	}

</script>
</html>