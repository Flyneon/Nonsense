<%@page import="java.util.List"%>
<%@page import="modelManagement.PromotionManager"%>
<%@page import="models.Promotion"%>
<%@page import="modelManagement.TypeNewsManager" %>
<%@page import="models.TypeNews" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<%
String idpt;
idpt=request.getParameter("id");
System.out.println("----------idpt:"+idpt);
Promotion pt;
PromotionManager ptm = new PromotionManager();
pt=ptm.getById(idpt);
TypeNewsManager tnm = new TypeNewsManager();
List<TypeNews> typeNewsList = tnm.getAllNews();
%>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<div>
		<form id="dulieu" method="post" action="promotionSave.jsp">
			<input type="hidden" name = "id" value="<%=(idpt!=null)?pt.getId():"" %>">
			<table border="1">
				<tr>
					<td>Thêm mới</td>
					<td>Dữ liệu</td>				
				</tr>
				<tr>
					<td>Tên</td>
					<td><input type="text" name="proName" value="<%=(idpt!=null)?pt.getProName():""%>"></td>				
				</tr>
				<tr>
					<td><input type="file" name="images" width=100px;></td>
				</tr>	
				<tr>
					<td> Tiêu đề</td>
					<td>
					<textarea rows="5" cols="40" name="brief" id="brief" ><%=(idpt!=null?pt.getBrief():"") %></textarea>
					</td>				
				</tr>	
				<tr>
					<td>Nội dung</td>
					<td><textarea rows="10" cols="40" name="description"  id="description" style="width: 100% ; height: 100%;" ><%=(idpt!=null)?pt.getDescription():""%></textarea></td>	\			
				</tr>
				<tr>
					<td>Trạng thái</td>
					<td><select name="proStatus">
					<option value="1"> Hiển thi</option>
					<option value="0">Không hiển thị</option>
					</select></td>				
				</tr>		
				<tr>
					<td>Loại tin</td>
					<td><select name="typeNewsId" >
					<% 
					for(TypeNews tn : typeNewsList){
					%>
						<option value="<%=tn.getId()%>"><%=tn.getTypeNewsName() %></option>
					<%}%>
					</select></td>				
				</tr>	
			</table>
			<input type="submit" value="Lưu">
			<input type="reset" value="Nhập lại">
		</form>
		<script type="text/javascript">
		CKEDITOR.replace( 'brief' );
		CKEDITOR.replace( 'description' );
		</script>
	</div>