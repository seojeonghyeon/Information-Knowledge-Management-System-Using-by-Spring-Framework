<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page session="true" %>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page import="org.zerock.ManageService.ModifyAssets" %>
<%@ page import="java.util.ArrayList"%>

	<script  type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js" charset="UTF-8"></script> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<!-- bring the DB data: QRcode image and data  -->
<!-- update the data and repeat -->
<style> 
	#topMenu {
	height: 30px; 
	width: 850px;
	} 
	#topMenu ul li {
	list-style: none; 
	color: white; 
	background-color: #2d2d2d; 
	float: left; 
	line-height: 30px;
	vertical-align: middle; 
	text-align: center;
	} 
	#topMenu .menuLink {
	text-decoration:none; 
	color: white; 
	display: block; 
	width: 150px; 
	font-size: 12px;
	font-weight: bold; 
	font-family: "Trebuchet MS", Dotum, Arial; 
	} 
	#topMenu .menuLink:hover { 
	color: red;
	background-color: #4d4d4d;
	} 
</style>
<%@include file="../include/header.jsp" %>

<%

String user_Code = "";
user_Code = (String)session.getAttribute("user_Code");
ModifyAssets Clientuser = new ModifyAssets(user_Code);
ArrayList<String> asset_Class = Clientuser.getAssetsClass();
ArrayList<String> asset_Number = Clientuser.getAssetsNumber();
ArrayList<String> asset_Code = Clientuser.getAssetsCode();
ArrayList<String> asset_Info = Clientuser.getAssetsInfo();
ArrayList<String> qr_Image = Clientuser.getAssetsImage();

for(int i=0; i<asset_Class.size(); i++){
	String SizeName = "";
	String content = asset_Info.get(i);
%>

<div style="background:white;">
<br>
	<a href="#" style="float:left;" onclick="window.open('../resources/QRwindow.jsp?content=<%= content %>','','width=260,height=260, toolbars=no,resizable=yes,scrollbars=no')">
			<img src="<%=qr_Image.get(i)%>" style="margin:10px;" width="40px" height="40px" align="left" alt="QRcode" class="hidden-xs hidden-sm">
			<img src="<%=qr_Image.get(i)%>" style="margin:10px;" width="40px" height="40px" align="left" alt="QRcode" class="visible-xs visible-sm circle-logo">
	</a>
	<% if(asset_Class.get(i).equals("도서")){
		SizeName = "페이지 수";
	}else if(asset_Class.get(i).equals("식품")){
		SizeName = "유통 기한";
	}
	%>
	<p> 자산의  코드  = <%=asset_Code.get(i)%></p>
	<% String[] array = asset_Info.get(i).split("/"); %>
	 <p>대분류 : <%=asset_Class.get(i)%>, 이름 :  <%= array[1] %>,제작자 : <%= array[2] %>, <%= SizeName %> : <%=array[3]%></p>
	<br>
	<form name="viewerForm" action="deleteProcess" method="post">
	<div>
		<input type="text" value="<%=asset_Code.get(i)%>" name="asset_Code" id="asset_Code" style="display:none"/>
		<input type="submit" id="delete" value="이 자산을 삭제" name="delete" onclick="exec('viewerForm','<%=asset_Code.get(i)%>')">
	</div>
	</form>
</div>
<hr>
<% } %>
<%@include file="../include/footer.jsp" %>