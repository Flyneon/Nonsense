<%@page import="modelManagement.FoodManagement"%>
<%@page import="models.Food"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>

<%
	Food fd =new Food();
	fd.setFoodName(request.getParameter("foodName"));
	fd.setImage(request.getParameter("image"));
	fd.setCost(Integer.parseInt(request.getParameter("cost")));
	System.out.println("foodGroupID:"+request.getParameter("foodGroupId"));
	fd.setFoodGroupId(Integer.parseInt(request.getParameter("foodGroupId")));
	FoodManagement fdm = new FoodManagement();
	
	String id;
	id = request.getParameter("id");
	if(id.length()<1){
		fdm.insert(fd);
	}else{
		fd.setId(Integer.parseInt(id));
		fdm.update(fd);
	}
	response.sendRedirect("foodList.jsp");
%>