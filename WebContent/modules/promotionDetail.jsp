<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Promotion" %>
<%@page import="modelManagement.PromotionManager" %>
<%@page pageEncoding="utf-8" language="java" %>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<%
String idpt = request.getParameter("id");
Promotion pt;
PromotionManager ptm = new PromotionManager();
pt = ptm.getById(idpt);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Quản lý nhà hàng</title>
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
</head>
<body>
	<div class="main">
	<jsp:include page="banner.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
		<div class="content">
			<div class="box" style="border: 0px;">
		<%if(idpt!=null){ %>
			<p class="headline"><%=pt.getProName() %><br></p>
			<%=pt.getDescription() %>
			</div>
			</div>
			<%} %>
		<div class="clear"></div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>