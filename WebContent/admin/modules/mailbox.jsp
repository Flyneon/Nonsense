<%@page import="models.OrderGroup"%>
<%@page import="modelManagement.OrderGroupManager"%>
<%@page import="modelManagement.OrderDetail2Manager"%>
<%@page import="models.OrderDetail2"%>
<%@page import="modelManagement.OrdersManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Orders"%>
<%@page pageEncoding="utf-8" language="java"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%
	OrderGroupManager ogm = new OrderGroupManager();
	List<OrderGroup> orderGroupList = ogm.getAllGroup();
	OrderGroup og = null;
	String ogId = request.getParameter("id");
	if (ogId != null && ogId.trim().length() > 0) {
		og = ogm.getById(ogId);
	}
	
	String id=request.getParameter("ogId");
	String status=request.getParameter("odStatus");
	OrdersManager odm = new OrdersManager();
	Orders os = new Orders();
	if(id!=null){
		/* SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=df.parse(d1);
		String deliveryDate= df.format(date1);
		System.out.println("-------------deliveryDate:"+ deliveryDate); */
		os.setDeliveryDate(request.getParameter("deliveryDate"));
		os.setCloseDate(request.getParameter("closeDate"));
		System.out.println("-------------deliveryDate:"+ os.getDeliveryDate());
		os.setOdStatus(status);
		odm.update(id,os);
		response.sendRedirect("mailbox.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/styleAdmin.css" />
<title>Quản lý đặt món</title>
<script type="text/javascript">
	function showForm() {
		document.getElementById("orderForm").style.display = "block";
	}

	function closeForm() {
		document.getElementById("orderForm").style.display = "none";
		/* document.getElementById("invForm").style.display="none";
		document.getElementById("invEditForm").style.display="none"; */
	}
</script>
</head>
<body>
	<script type="text/javascript">
	function openForm(){
		var xmlhttp;
		document.getElementById("invoicesFormArea").innerHTML="Page loading ...";
		if(window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
			xmlhttp.open("get", "invoicesDetail.jsp", true)
			xmlhttp.send();
			xmlhttp.onreadystatechange= function () {
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
					invForm = document.getElementById("invoicesFormArea");
					invForm.innerHTML = xmlhttp.responseText();
					invForm.style.position = "absolute";
					invForm.style.left=100;
					invForm.style.top=100;
					invForm.style.display="block";
					invForm.style.zIndex="100";
				}else{
					document.getElementById("invoicesFormArea").innerHTML="Page error";
				}
				
			}
		}else{
			document.getElementById("invoicesFormArea").innerHTML="Khong ho tro Ajax";
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
			<table style="overflow: auto;">
			<h1 style="text-align: center;">Quản lý đơn hàng</h1>
				<thead>
					<tr>
						<td width="5%">STT</td>
						<td width="5%">Mã đơn hàng</td>
						<td width="7%">Tên khách hàng</td>
						<td width="10%">Ngày mua hàng</td>
						<td width="10%">Ngày giao hàng</td>
						<td width="10%">Ngày đóng đơn hàng</td>
						<td width="8%">Trạng thái</td>
						<td width="8%">Sửa đơn hàng</td>
					</tr>
				</thead>
				<tbody>
					<%
						if (orderGroupList == null || orderGroupList.isEmpty()) {
					%>
					<tr>
						<td>Không có CSDL</td>
					</tr>
					<%
						} else {
					%>
					<%
						int i = 0;
							for (OrderGroup orderGroup : orderGroupList) {
								i++;
					%>
					<tr>
						<td><%=i%></td>
						<td><%=orderGroup.getId()%></td>
						<td><%=orderGroup.getCusName()%></td>
						<td><%=orderGroup.getOrderDate()%></td>
						<td><%=orderGroup.getDeliveryDate()%></td>
						<td><%=orderGroup.getCloseDate()%></td>
						<td><%=orderGroup.getOdStatus()%></td>
						<td><a href="mailbox.jsp?id=<%=orderGroup.getId()%>" >Sửa</a></td>
					</tr>
					<%
							}
						}
					%>

				</tbody>
			</table>
		</div>
		<div id="orderForm" style="float: left; width: 90%; margin-left:4% ;display:<%=(og != null) ? "block" : "none"%> ; ">
		<div id="invForm" style="float:left; width:30%; margin: 30px; text-align: left; border: 1px solid black;">
			<h1 style="text-align: center">Chi tiết đơn hàng</h1>
				<table>
					<tr>
						<td>Mã đơn hàng</td>
						<td><%=(og!=null)?og.getId():""%></td>
					</tr>
					<tr>
						<td>Tên khách hàng</td>
						<td><%=(og!=null)?og.getCusName():""%></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><%=(og!=null)?og.getEmail():""%></td> 
					</tr>
					<tr>
						<td>Địa chỉ</td>
						<td><%=(og!=null)?og.getAddress():""%></td>
					</tr>
					<tr>
						<td>Số điện thoại</td>
						<td><%=(og!=null)?og.getPhone():""%></td>
					</tr>
					<tr>
						<td>Ghi chú</td>
						<td><%=(og!=null)?og.getDescription():""%></td>
					</tr>
					<tr>
						<td>Tên sản phẩm</td>
						<td><%=(og!=null)?og.getFoodName():""%></td>
					</tr>
					<tr>
						<td>Số Lượng</td>
						<td><%=(og!=null)?og.getNumber():""%></td>
					</tr>
					<tr>
						<td>Tổng giá</td>
						<td><%=(og!=null)?og.getTotalCost():""%></td>
					</tr>
					<tr>
						<td>Ngày đặt hàng</td>
						<td><%=(og!=null)?og.getOrderDate():""%></td>
					</tr>
					<tr>
						<td>NGày giao hàng</td>
						<td><%=(og!=null)?og.getDeliveryDate():""%></td>
					</tr>
					<tr>
						<td>Ngày kết thúc đơn hàng</td>
						<td><%=(og!=null)?og.getCloseDate():""%></td>
					</tr>
					<tr>
						<td>Trạng thái</td>
						<td><%=(og!=null)?og.getOdStatus():""%></td>
					</tr>
				</table>
		</div>
		<div id="invEditForm" style="float:right; width: 50%; margin: 30px; text-align: left; border: 1px solid black; ">
		<form action="mailbox.jsp" method="post">
		<input type="hidden" name="ogId" value="<%=(og!=null)?og.getId():""%>">
			<h1 style="text-align: center">Chỉnh sửa  đơn hàng</h1>
				<table>
					<tr>
						<td>Mã đơn hàng</td>
						<td><%=(og!=null)?og.getId():"" %></td>
					</tr>
					<tr>
						<td>Tên khách hàng</td>
						<td><%=(og!=null)?og.getCusName():""%></td>
					</tr>
					<tr>
						<td>Ngày đặt hàng</td>
						<td><%=(og!=null)?og.getOrderDate():""%></td>
					</tr>
					<tr>
						<td>Ngày giao hàng</td>
						<td><input name = "deliveryDate" type="date" value="<%=(og!=null?og.getDeliveryDate():"")%>"></td>
					</tr>
					<tr>
						<td>Ngày đóng đơn hàng</td>
						<td><input name = "closeDate" type="date"></td>
					</tr>
					<tr>
						<td>Trạng thái</td>
						<td><select name="odStatus">
						<option value="Mới đặt"> Mới đặt</option>
						<option value="Nhận đơn hàng">Nhận đơn hàng</option>
						<option value="Đã giao hàng">Đã giao hàng</option>
						</select></td>
					</tr>
				</table>
				<div>
					<input type="submit" value="Sửa">
					<input type="reset" value="Nhập lại"> 
					<input type="button" value="Thoát" onclick="closeForm()">
				</div>
			</form>
		</div>
		</div>
	</div>
</body>
</html>