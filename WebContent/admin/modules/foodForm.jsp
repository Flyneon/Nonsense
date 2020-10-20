<%@page import="java.util.List"%>
<%@page import="modelManagement.FoodManagement"%>
<%@page import="models.Food"%>
<%@page import="modelManagement.FoodGroupManager" %>
<%@page import="models.FoodGroup" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<%
String idfd;
idfd=request.getParameter("id");
Food fd;
FoodManagement fdm = new FoodManagement();
fd=fdm.getById(idfd);
FoodGroupManager fgm = new FoodGroupManager();
List<FoodGroup> foodGrList = fgm.getAllFood();
%>
<html>
<head>
</head>
<body>
	<div>
		<form id="dulieu" method="post" action="foodSave.jsp">
			<input type="hidden" name = "id" value="<%=(idfd!=null)?fd.getId():"" %>">
			<table border="1">
				<tr>
					<td>Thêm mới</td>
					<td>Dữ liệu</td>				
				</tr>
				<tr>
					<td>Tên</td>
					<td><input type="text" name="foodName" value="<%=(idfd!=null)?fd.getFoodName():""%>"></td>				
				</tr>	
				<tr>
					<td> Ảnh minh họa</td>
					<td><input type="file" name= "image" src="<%=(idfd!=null)?fd.getImage():""%>"></td>				
				</tr>	
				<tr>
					<td>Giá</td>
					<td><input type="text" name = "cost" value="<%=(idfd!=null)?fd.getCost():""%>">
					</td>				
				</tr>	
				<tr>
					<td>Loại món ăn</td>
					<td><select name="foodGroupId">
					<% 
					for(FoodGroup fg : foodGrList){
					%>
						<option value="<%=fg.getId()%>"><%=fg.getGroupName() %></option>
					<%}%>
					</select></td>				
				</tr>	
			</table>
			<input type="submit" value="Lưu">
			<input type="reset" value="Nhập lại">
		</form>
	</div>
</body>
</html>