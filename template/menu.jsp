<ul id='main_nav_bar' class='nav_bar'>
	<li>
		<a class='menu' id='order_link' href='../order/select_location.jsp?user=<% if (dJson == null)  {out.println(uJson);} else {out.println(uJson+"&driver="+dJson);} %> name='order_link'>
			<h3>ORDER</h3>
		</a>
	</li>
	<li>
		<a class='menu' id='history_link' href='../history/transaction_history.jsp?user=<% if (dJson == null)  {out.println(uJson);} else {out.println(uJson+"&driver="+dJson);} %> name='history_link'>
			<h3>HISTORY</h3>
		</a>
	</li>
	<li>
		<a class='menu' id = 'profile_link' href='../profile/profile.php?user=<% if (dJson == null)  {out.println(uJson);} else {out.println(uJson+"&driver="+dJson);} %> name='profile_link'>
			<h3>MY PROFILE</h3>
		</a>
	</li>
</ul>