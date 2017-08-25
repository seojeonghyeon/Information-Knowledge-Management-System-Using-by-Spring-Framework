<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page import="org.zerock.ManageService.ModifyAssets" %>
<%@ page import="org.json.*"%>
<%@ page import="java.util.ArrayList"%>
<%
String user_ID = "";
user_ID = (String)session.getAttribute("user_ID");
ModifyAssets Clientuser = new ModifyAssets(user_ID, 1);
JSONObject jobj = new JSONObject();
jobj.put("android", Clientuser.jsonCreate());
%>
<%= jobj%>