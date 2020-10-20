<%@page import="modelManagement.FoodGroupManager"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>

<%
	FoodGroupManager fgm = new FoodGroupManager();
	
	String id;
	id = request.getParameter("id");
	fgm.deleteId(id);
	response.sendRedirect("groupFood.jsp");
%>