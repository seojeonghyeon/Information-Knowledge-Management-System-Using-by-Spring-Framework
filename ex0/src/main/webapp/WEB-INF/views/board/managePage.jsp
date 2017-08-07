<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page import="org.zerock.ManageService.ModifyAssets" %>
<%@ page import="java.util.ArrayList"%>

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
<nav id="topMenu"> 
	<ul> 
		<li>
			<a href="#" class="menuLink" onclick="window.open('resources/dashboard/QRcreate.html','','width=500,height=300, toolbars=no,resizable=yes,scrollbars=no')">QRcode 생성</a>
		</li> 
		<li>
			<a href="#" class="menuLink">QRcode 삭제</a>
		</li>
	</ul>
</nav>

<%

String user_Code = "";
user_Code = (String)session.getAttribute("user_Code");
ModifyAssets Clientuser = new ModifyAssets(user_Code);
ArrayList<String> asset_Class = Clientuser.getAssetsClass();
ArrayList<String> asset_Number = Clientuser.getAssetsNumber();
ArrayList<String> asset_Code = Clientuser.getAssetsCode();
ArrayList<String> asset_Info = Clientuser.getAssetsInfo();
//ArrayList<String> qr_Image = Clientuser.getAssetsImage();

for(int i=0; i<asset_Class.size(); i++){

%>


<div style="background:white;">
<br>
	<a href="#" style="float:left;" onclick="window.open('resources/dashboard/QRwindow.html','','width=260,height=260, toolbars=no,resizable=yes,scrollbars=no')">
			<img src="resources/images/QRgen.png" style="margin:10px;" width="40px" height="40px" align="left" alt="QRcode" class="hidden-xs hidden-sm">
			<img src="resources/images/QRgen.png" style="margin:10px;" width="40px" height="40px" align="left" alt="QRcode" class="visible-xs visible-sm circle-logo">
	</a>
	<form style="float:right; margin:5px;" border="1px">
	<center>
	<a href="" style="float:right;" onclick="window.open('resources/dashboard/NFCwindow.html','','width=800,height=400, toolbars=no,resizable=yes,scrollbars=no')">
			<img src="resources/images/nfc.png" width="40px" height="40px" alt="NFC" class="hidden-xs hidden-sm">
			<img src="resources/images/nfc.png" width="40px" height="40px"  alt="NFC" class="visible-xs visible-sm circle-logo">
	</a>
	<p>View NFC</p>
	</center>
	</form>
	<p><%=asset_Class.get(i)%> : <%=asset_Number.get(i)%></p>
	<p><%=asset_Info%></p>
	<br>
</div>
<hr>
<% } %>

<%@include file="../include/footer.jsp" %>