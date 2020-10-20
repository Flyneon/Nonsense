<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Food"%>
<%@page import="java.util.Date" %>
<%@page import="modelManagement.FoodManagement"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="utf-8" language="java"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%
	String idfd;
	idfd = request.getParameter("id");
	Food fd;
	FoodManagement fdm = new FoodManagement();
	fd = fdm.getById(idfd);
%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html;chasert= UTF-8" />
<title>Giỏ hàng</title>
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<script type="text/javascript">
	function tCost(x) {
		var totalCost;
		var number = document.getElementById("idNumber").value;
		totalCost = x * number;
		if (totalCost > 0)
			document.getElementById("totalCost").innerHTML = totalCost;
	}
</script>
</head>
<body>
	<script type="text/javascript">
	function goback() {
	    history.back(-1)
	}
	</script>
	<div class="main">
	<jsp:include page="banner.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
		<div class="content"> 
		<form method="post" action="shoppingCartSave.jsp">
				<table style="border: 1px solid black; width:500px;">
					<thead>
						<tr>
							<th>Món ăn</th>
							<th>Số lượng</th>
							<th>Giá bán</th>
							<th>Thành tiền</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td ><%=fd.getFoodName()%></td>
							<td><input type="number" name="number" id="idNumber" style="margin-right:20px; width:50px;">
							<input type="button" value="Thành tiền" onclick="tCost(<%=fd.getCost()%>)" /></td>
							<td><%=fd.getCost()%></td>
							<td><p id="totalCost"></p></td>

						</tr>
					</tbody>
				</table>
				<input type="hidden" name="id" value="<%=(idfd != null) ? fd.getId() : ""%>">
				<div class="sc">
					<ul class=>
						<li>Họ và tên</li>
						<li><input type="text" width="150px" height="auto" name="cusName" /></li>
					</ul>

					<ul>
						<li>Email</li>
						<li><input type="text" width="150px" height="auto" name="email" /></li>
					</ul>

					<ul>
						<li>Địa chỉ</li>
						<li><input type="text" width="150px" height="auto" name="address" /></li>
					</ul>

					<ul>
						<li>Số điện thoại</li>
						<li><input type="text" width="150px" height="auto" name="phone" /></li>
					</ul>

					<ul>
						<li>Ghi chú</li>
						<li><textarea rows="5" cols="40" name="description"></textarea></li>
					</ul>
					<ul>
						<li><input type="submit" value="Thanh toán"></li>
						<li><input type="button" onclick="goback()" value="Quay trở lại chọn món"></li>
					</ul>
				</div>
			</form> 
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>