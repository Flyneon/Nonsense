<%@page import="java.util.List" %>
<%@page import="models.TypeNews"%>
<%@page import="modelManagement.TypeNewsManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
TypeNewsManager tnm= new TypeNewsManager();
TypeNews tn = null;

String tnId = request.getParameter("id");
if(tnId!=null&&tnId.trim().length()>0){
	tn= tnm.getById(tnId);
}

String typeNewsName=request.getParameter("typeNewsName");
String id=request.getParameter("tnId");
if(typeNewsName!=null&&typeNewsName.trim().length()>0){
	if(id==null || id.trim().length()<1){
		tnm.insert(typeNewsName);
	}else{
		tnm.updateId(id, typeNewsName);
	}
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styleAdmin.css" />
<script type="text/javascript">
	  		function showForm(){
	  			document.getElementById("tnForm").style.display = "block";
	  		}
	  		
	  		function closeForm(){
	  			document.getElementById("tnForm").style.display = "none";
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
			<div style="float:left; width: 60%;text-align: left; border: 1px solid black;" >
				<p><b>Danh sách Loại tin</b></p>
				<table >
					<tr style="background-color: blue; color: white;">
						<th>STT</th>
						<th>Tên </th>
						<th>Sửa</th>
						<th>Xóa</th>
					</tr>
					<%
					List<TypeNews> tnList = tnm.getAllNews();
					if(tnList.isEmpty()){
					%>
						<tr>
							<td colspan="3">Chưa có loại bài viết trong CSDL</td>
						</tr>
					<%
					} else {
						int i = 0;
						for(TypeNews typeNews : tnList){
					%>
							<tr>
								<td><%=(++i) %></td>
								<td><%=typeNews.getTypeNewsName() %></td>
								<td><a href="typeNews.jsp?id=<%= typeNews.getId()%>">Sửa</a></td>
								<td><a href="deleteTypeNews.jsp?id=<%= typeNews.getId()%>">Xóa</a></td>
							</tr>
					<%
						}
					}
					%>
				</table>
				<input type="button" value="Thêm mới" onclick="showForm()"/>
			</div>
			<div id="tnForm" style="float:left; width: 30%; margin-left: 2px; text-align: left; border: 1px solid black; display:<%=(tn!=null)?"block":"none"%>;">
				<form action="typeNews.jsp" method="post" style="float: left;">
					<input type="hidden" name="tnId" value="<%=(tn!=null)?tn.getId():""%>">
					<div style="width: 100%; float:left;">
						<div style="float:left; width: 20%">Tên</div>
						<div style="float:left; width: 70%; padding-left: 2px;">
							<input type="text" name="typeNewsName" value="<%=(tn!=null)?tn.getTypeNewsName():""%>">
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