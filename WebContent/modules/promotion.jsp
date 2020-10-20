<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modelManagement.PromotionManager"%>
<%@page import="models.Promotion"%>
<%@page pageEncoding="utf-8" language="java" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<html>
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
			<%
				PromotionManager ptm = new PromotionManager();
				List<Promotion> promotionList = ptm.getAllPromotion();
				for(Promotion pt: promotionList){%>
					<div class=box-new>
					<div class="box-left">
					<img src="../images/<%=pt.getImages()%>" height="290px" width="200px" />
				</div>
				<div class="box-right">
					<p class="headline-news"><%=pt.getProName() %></p>
					<p class="abridge"><%=pt.getBrief()%></p>
					<a href="promotionDetail.jsp?id=<%=pt.getId()%>" style="float: right;">Đọc tiếp</a>
				</div>
			</div>
				<%} %>
		</div>
		<div class="clear"></div>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>