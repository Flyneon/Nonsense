<%@page import="modelManagement.PromotionManager"%>
<%@page import="models.Promotion"%>
<%@page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

%>

<%
	Promotion pt =new Promotion();
	pt.setProName(request.getParameter("proName"));
	pt.setBrief(request.getParameter("brief"));
	pt.setDescription(request.getParameter("description"));
	pt.setProStatus(Integer.parseInt(request.getParameter("proStatus")));
	pt.setTypeNewsId(Integer.parseInt(request.getParameter("typeNewsId")));
	pt.setImages(request.getParameter("images"));
	System.out.println("------------:"+request.getParameter("images"));
	PromotionManager ptm = new PromotionManager();
	
	String id;
	id = request.getParameter("id");
	if(id.length()<1){
		ptm.insert(pt);
	}else{
		ptm.update(id,pt);
	}
	response.sendRedirect("promotionList.jsp");
%>
