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
	FoodManagement fdm = new FoodManagement();
	
	String id;
	id = request.getParameter("id");
	fdm.deleteId(id);
	response.sendRedirect("foodList.jsp");
%>