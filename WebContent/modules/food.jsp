<%@page import="models.FoodGroup"%>
<%@page import="modelManagement.FoodGroupManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Food"%>
<%@page import="modelManagement.FoodManagement"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Chi tiết món ăn</title>
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
</head>
<body>
	<div class="main">
	<jsp:include page="banner.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
		<div class="content">
		<div class="sub-menu">
		<ul>
		<%
		FoodGroupManager fgm =new FoodGroupManager();
		List<FoodGroup> fgList = fgm.getAllFood();
		for(FoodGroup fg: fgList){
		%>
		<li><a href="food.jsp?id=<%=fg.getId() %>"> <%=fg.getGroupName() %></a></li>
		<%}%>
		</ul>
		</div>
		
		
		
			<%
				String id=request.getParameter("id");
				FoodManagement fmg = new FoodManagement();
				List<Food> foodList = fmg.getAllFood();
				if(id==null||id.isEmpty()){
				for (Food fd : foodList) {
			%>
			<form method ="post" action="shoppingCart.jsp?id=<%=fd.getId()%>">
			<div class="box-child">
				<div class=box-images><img alt="" src="../images/food/<%=fd.getImage()%>" width="100%"></div>
				<div class ="box-name" ><%=fd.getFoodName()%> - <%=fd.getCost() %> VNĐ</div>
				<div style="margin:10px 0 10px 110px;">
				<input type="submit" class="btn-add-food" value="Đặt hàng" ></div>
			</div>
			</form>
			<%}
				}else{
					for(Food fd:foodList){
						if(fd.getFoodGroupId()==Integer.parseInt(id)){
			%>
				<form method ="post" action="shoppingCart.jsp?id=<%=fd.getId()%>">
			<div class="box-child">
				<div class=box-images><img alt="" src="../images/food/<%=fd.getImage()%>" width="100%"></div>
				<div class ="box-name" ><%=fd.getFoodName()%> - <%=fd.getCost() %> VNĐ</div>
				<div style="margin:10px 0 10px 110px;">
				<input type="submit" class="btn-add-food" value="Đặt hàng" ></div>
			</div>
			</form>
			<%
						}
					}
				}
			%>
		</div>
		<div class="clear"></div>
		<jsp:include page="footer.jsp"></jsp:include>>
	</div>
</body>
</html>