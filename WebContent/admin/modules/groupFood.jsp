<%@page import="java.util.List" %>
<%@page import="models.FoodGroup"%>
<%@page import="modelManagement.FoodGroupManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<%
FoodGroupManager fgm= new FoodGroupManager();
FoodGroup fg = null;

String fgId = request.getParameter("id");
if(fgId!=null&&fgId.trim().length()>0){
	fg= fgm.getById(fgId);
}

String groupName=request.getParameter("groupName");
String id=request.getParameter(fgId);
if(groupName!=null&&groupName.trim().length()>0){
	if(id==null || id.trim().length()<1){
		fgm.insert(groupName);
	}else{
		fgm.updateId(id, groupName);
	}
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styleAdmin.css" />
<script type="text/javascript">
	  		function showForm(){
	  			document.getElementById("fgForm").style.display = "block";
	  		}
	  		
	  		function closeForm(){
	  			document.getElementById("fgForm").style.display = "none";
	  		}
  		</script>
</head>
<body>

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
		<div style="float:left; width: 100%;">
			<div style="float:left; width: 60%;text-align: left; border: 1px solid black;" class="divBorder">
				<p><b>Danh sách Loại món ăn</b></p>
				<table border="1">
					<tr style="background-color: blue; color: white;">
						<th>STT</th>
						<th>Tên </th>
						<th>Sửa</th>
						<th>Xóa</th>
					</tr>
					<%
					List<FoodGroup> fgList = fgm.getAllFood();
					if(fgList.isEmpty()){
					%>
						<tr>
							<td colspan="3">Chưa có loại món ăn trong CSDL</td>
						</tr>
					<%
					} else {
						int i = 0;
						for(FoodGroup foodGr : fgList){
					%>
							<tr>
								<td><%=(++i) %></td>
								<td><%=foodGr.getGroupName() %></td>
								<td><a href="groupFood.jsp?id=<%= foodGr.getId()%>">Sửa</a></td>
								<td><a href="deleteGroupFood.jsp?id=<%= foodGr.getId()%>">Xóa</a></td>
							</tr>
					<%
						}
					}
					%>
				</table>
				<input type="button" value="Thêm mới" onclick="showForm()"/>
			</div>
			<div id="fgForm" style="float:left; width: 30%; margin-left: 2px; text-align: left; border: 1px solid black; display:<%=(fg!=null)?"block":"none"%>;">
				<form action="groupFood.jsp" method="post" style="float: left;">
					<input type="hidden" name="fgId" value="<%=(fg!=null)?fg.getId():""%>">
					<div style="width: 100%; float:left;">
						<div style="float:left; width: 20%">Tên</div>
						<div style="float:left; width: 70%; padding-left: 2px;">
							<input type="text" name="groupName" value="<%=(fg!=null)?fg.getGroupName():""%>">
						</div>
					</div>
					<div style="width: 100%; float:left; margin-top: 5px;">
						<center>
							<input type="submit" value="Lưu">
							<input type="reset" value="Nhập lại" style="margin-left: 5px;"> 
							<input type="button" value="Thoát" onclick="closeForm()" style="margin-left: 5px;"/>
						</center>
					</div>
				</form>
			</div>
		</div>
		</div>
	</div>
</body>
</html>