<%@page import="java.util.ArrayList"%>
<%@page import="models.Promotion"%>
<%@page import="models.TypeNews" %>
<%@page import="modelManagement.PromotionManager" %>
<%@page import="modelManagement.TypeNewsManager"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styleAdmin.css" />
<title>Danh sách Tin khuyến mại</title>
</head>
<body>
	<script type="text/javascript">
	function openForm(){
		var xmlhttp;
		document.getElementById("promotionFormArea").innerHTML="Page is loading";
		if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET", "promotionForm.jsp", true);
			xmlhttp.send();
			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
					cusForm = document.getElementById("promotionFormArea");
					cusForm.innerHTML=xmlhttp.responseText;
					cusForm.style.position = "absolute";
					cusForm.style.left=100;
					cusForm.style.top=100;
					cusForm.style.display="block";
					cusForm.style.zIndex="100";
					
					massLayer = document.getElementById("massLayer");
					massLayer.style.position="absolute";
					massLayer.style.left=0;
					massLayer.style.top=0;
					massLayer.style.display="block";
					massLayer.style.zIndex="50";
					massLayer.style.width="100%";
					massLayer.style.height="100%";
					massLayer.style.opacity=0.65;
					}else{
						document.getElementById("promotionFormArea").innerHTML="Error";
					}
			}
		}
		else{
			document.getElementById("promotionFormArea").innerHTML="Khong ho tro ajax";
		}
	}
	</script>
	<div class="main">
		<div class="banner">
			<p
				style="text-align: center; font-size: 24px; font-weight: bold; padding-top: 50px">
				Quản lý hệ thống</p>
		</div>
		<div class="menu">
			<ul>
				<li>Quản lý thành viên</li>
				<li>Quản lý đặt bàn</li>
				<li ><a href="mailbox.jsp">Quản lý đặt món</a></li>
				<li><a href="foodList.jsp">Quản lý món ăn</a>
					<ul class="sub-menu">
						<li><a href="groupFood.jsp">Quản lý loại món ăn</a></li>
						<li><a href="foodList.jsp">Quản lý món ăn</a></li>
					</ul>
				</li>
				<li><a href="#">Quản lý bài viết</a>
					 <ul class="sub-menu">
						<li><a href="typeNews.jsp">Quản lí loại tin</a></li>
						<li><a href="promotionList.jsp">Quản lý bài viết</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="content">
		<h1 style="text-align: center;">Quản lý bài viết</h1>
			<table style="table-layout: auto;">
				<thead>
					<tr>
						<td>STT</td>
						<td>Tên bài viết</td>
						<td> Ảnh minh họa</td>
						<th>Tóm tắt</th>
						<th>Nội dung</th>
						<th>Trạng thái</th>
						<th>Loai tin</th>
						<th>Sửa</th>
						<th>Xóa</th>
					</tr>
				</thead>
				<tbody style="table-layout: auto;">
					<%
						PromotionManager ptm= new PromotionManager();
						TypeNewsManager tnm = new TypeNewsManager();
						List<Promotion> promotionList=ptm.getAllPromotion();
						List<TypeNews> typeNewsList = tnm.getAllNews();
						if (promotionList == null || promotionList.isEmpty()) {
					%>
					<tr>
						<td colspan="6">Chưa có csdl</td>
					</tr>
					<%} else {%>
					<%
						for (Promotion pt : promotionList) {
							for(TypeNews tn : typeNewsList){
								if(pt.getTypeNewsId()==tn.getId()){
					%>
					<tr>
						<td><%=pt.getId()%></td>
						<td><%=pt.getProName()%></td>
						<td><%=pt.getImages() %></td>
						<td style="overflow: hidden;"><%=pt.getBrief()%></td>
						<td style="overflow: hidden;"><%=pt.getDescription()%></td>
						<td><%if(pt.getProStatus()==0){%>
							Không hiển thị
							<%}else if(pt.getProStatus()==1){%>
							Hiển thị
							<%}%></td>
						<td><%=tn.getTypeNewsName() %></td>
						<td><a href="promotionForm.jsp?id=<%=pt.getId()%>"> Sửa </a></td>
						<td><a href="deletePromotion.jsp?id=<%=pt.getId()%>">Xóa</a></td>
					</tr>
					<%
								}
							}
						}
					%>
					<%
					}
					%>
				</tbody>
			</table>
			<div style="margin: 0 0 50px 40%;">
			<input type="button" onclick="openForm()" value="Thêm mới" style="margin-top: 50px;">
			</div>
		</div>
		<div class = "massLayer" style="display: none; background-color: white"></div>
	<div id="promotionFormArea" style="display: none; margin: 5px;background-color: white;border: 1px solid ;">
		Không có dữ liệu
	</div>
	</div>
</body>
</html>