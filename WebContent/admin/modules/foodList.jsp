<%@page import="java.util.ArrayList"%>
<%@page import="models.Food"%>
<%@page import="models.FoodGroup" %>
<%@page import="modelManagement.FoodGroupManager" %>
<%@page import="modelManagement.FoodManagement"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/styleAdmin.css" />
<title>Danh sách món ăn</title>
</head>
<body>
	<script type="text/javascript">
	function openForm(){
		var xmlhttp;
		document.getElementById("foodFormArea").innerHTML="Page is loading";
		if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET", "foodForm.jsp", true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
					cusForm = document.getElementById("foodFormArea");
					cusForm.innerHTML=xmlhttp.responseText;
					cusForm.style.position = "absolute";
					cusForm.style.left=100;
					cusForm.style.top=100;
					cusForm.style.display="block";
					cusForm.style.zIndex="100";
					
					massLayer = document.getElementById("massLayer");
					massLayer.style.position="absolute";
					massLayer.style.left=0;
					massLayer.style.top=0;
					massLayer.style.display="block";
					massLayer.style.zIndex="50";
					massLayer.style.width="100%";
					massLayer.style.height="100%";
					massLayer.style.opacity=0.65;
					}else{
						document.getElementById("foodFormArea").innerHTML="Error";
				}
			}
		}else{
			document.getElementById("foodFormArea").innerHTML="Khong ho tro ajax";
		}
	}
	</script>
	<div class="main">
		<div class="banner">
			<p
				style="text-align: center; font-size: 24px; font-weight: bold; padding-top: 50px">
				Quản lý hệ thống</p>
		</div>
		<div class="menu">
			<ul>
				<li>Quản lý thành viên</li>
				<li>Quản lý đặt bàn</li>
				<li ><a href="mailbox.jsp">Quản lý đặt món</a></li>
				<li><a href="foodList.jsp">Quản lý món ăn</a>
					<ul class="sub-menu">
						<li><a href="groupFood.jsp">Quản lý loại món ăn</a></li>
						<li><a href="foodList.jsp">Quản lý món ăn</a></li>
					</ul>
				</li>
				<li><a href="#">Quản lý bài viết</a>
					 <ul class="sub-menu">
						<li><a href="typeNews.jsp">Quản lí loại tin</a></li>
						<li><a href="promotionList.jsp">Quản lý bài viết</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="content">
		<h1 style="text-align: center;">Quản lý món ăn</h1>
			<table>
				<thead>
					<tr>
						<td >STT</td>
						<td>Tên món ăn</td>
						<th>Ảnh minh họa</th>
						<th>Giá</th>
						<th>Loại món ăn</th>
						<th>Sửa</th>
						<th>Xoá</th>
					</tr>
				</thead>
				<tbody>
					<%
						FoodGroupManager fgm= new FoodGroupManager();
						FoodManagement fdm = new FoodManagement();
						List<FoodGroup> foodGrList=fgm.getAllFood();
						List<Food> foodList = fdm.getAllFood();
						if (foodList == null || foodList.isEmpty()) {
					%>
					<tr>
						<td colspan="5">Chưa có csdl</td>
					</tr>
					<%} else {%>
					<%
						for (Food fd : foodList) {
							for(FoodGroup fg : foodGrList){
								if(fd.getFoodGroupId()==fg.getId()){
					%>
					<tr>
						<td ><%=fd.getId()%></td>
						<td><%=fd.getFoodName()%></td>
						<td><img src="../../images/food/<%=fd.getImage()%>" width="50px">
						</td>
						<td><%=fd.getCost()%></td>
						<td><%=fg.getGroupName() %></td>
						<td><a href="foodForm.jsp?id=<%=fd.getId()%>"> Sửa </a></td>
						<td><a href="deleteFood.jsp?id=<%=fd.getId()%>">Xóa</a></td>
					</tr>
					<%
								}
							}
						}
					%>
					<%
					}
					%>
				</tbody>
			</table>
			<div style="margin: 50px 0 50px 40%; ">
			<input type="button" onclick="openForm()" value="Thêm mới"></div>
			
		</div>
	<div id="foodFormArea" style="display: none; margin: 5px;background-color: white;border: 1px solid ;">
	<div class = "massLayer" style="display: none; background-color: white"></div>
	Không có dữ liệu
	</div>
	</div>
</body>
</html>