<%@page import="modelManagement.PromotionManager"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>

<%
	 PromotionManager ptm = new PromotionManager();
	String id;
	id = request.getParameter("id");
	ptm.deleteId(id);
	response.sendRedirect("promotionList.jsp");
%>