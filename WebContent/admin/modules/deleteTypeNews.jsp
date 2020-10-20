<%@page import="modelManagement.TypeNewsManager"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>

<%
	 TypeNewsManager tnm = new TypeNewsManager();
	String id;
	id = request.getParameter("id");
	if(id!=null||id.isEmpty()){
	tnm.deleteId(id);
	}
	response.sendRedirect("typeNews.jsp");
%>