<%@page import="modelManagement.OrderGroupManager"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>

<%
	OrderGroupManager ogm = new OrderGroupManager();
	
	String id;
	id = request.getParameter("id");
	ogm.delete(id);
	response.sendRedirect("mailbox.jsp");
%>