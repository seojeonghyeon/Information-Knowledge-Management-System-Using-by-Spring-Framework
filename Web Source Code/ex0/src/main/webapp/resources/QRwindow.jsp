<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page session="true" %>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page import="org.zerock.EncodeService.encodeProcess" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IKAMS</title>
</head>
<body>
<%
String googleQRUrl = "https://chart.googleapis.com/chart?chs=177x177&cht=qr&chl=";
String content = request.getParameter("content");
content=content.replace("%20", " ");
encodeProcess text = new encodeProcess(content);
String qrSRC = googleQRUrl + "content="+text.getEncode()+"&choe=UTF-8";
%>
<center>
<img src="<%= qrSRC %>" name ="qr" id="qr" style="margin:20px;" width="200px" height="200px" align="left" class="hidden-xs hidden-sm" border="1px">
</center>

</body>
</html>