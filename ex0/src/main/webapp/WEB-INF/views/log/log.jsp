<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="org.zerock.LogService.LogManage" %>
<%@ page import="java.util.ArrayList"%>

<%@include file="../include/header.jsp" %>

<style>
  table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
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
.green_window {
	display: inline-block;
	width: 366px; height: 34px;
	border: 3px solid #5584ff;
	background: white;
}
.input_text {
	width: 348px; height: 21px;
	margin: 6px 0 0 9px;
	border: 0;
	line-height: 21px;
	font-weight: bold;
	font-size: 16px;
	outline: none;
}
.sch_smit {
	width: 54px; height: 40px;
	margin: 0; border: 0;
	vertical-align: top;
	background: #5584ff;
	color: white;
	font-weight: bold;
	border-radius: 1px;
	cursor: pointer;
}
.sch_smit:hover {
	background: #5584ff;
}
</style>
<%
String user_Code = (String)session.getAttribute("user_Code");
LogManage clientuser = new LogManage(user_Code,"조회","");

ArrayList<Integer> number =clientuser.getnumbers();
ArrayList<String> activity_Contents =clientuser.getactivity_Contents();;
ArrayList<String> activity_Time =clientuser.activity_Time();
%>
<table width=100% style="margin-left: auto; margin-right: auto;">
	<thead style="text-align:center;">
		<tr style="background:#00558c;">
			<th>순서</th>
			<th>활동 내역</th>
			<th>활동 시간</th>
		</tr>
	</thead>
	<tbody style="text-align:center;">
	<% 
		int count = 0;
		for(int i=number.size()-1; i>0 ; i--){ 
			count++;
			if(count == 10)
				break;
		%>
		<tr>
			<td><%= number.get(i) %></td>
			<td><%= activity_Contents.get(i) %></td>
			<td><%= activity_Time.get(i)  %></td>
		</tr>
		<% } %>
	</tbody>
</table>
<!-- 
				<div class="box-footer">

					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="listPage${pageMaker.makeQuery(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="listPage${pageMaker.makeQuery(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="listPage${pageMaker.makeQuery(pageMaker.endPage +1) }">&raquo;</a></li>
							</c:if>

						</ul>
					</div>


					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="${idx}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="${pageMaker.endPage +1}">&raquo;</a></li>
							</c:if>

						</ul>
					</div>


				</div>
 <form id="jobForm">
  <input type='hidden' name="page" value=${pageMaker.cri.perPageNum}>
  <input type='hidden' name="perPageNum" value=${pageMaker.cri.perPageNum}>
</form>

<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
	
	$(".pagination li a").on("click", function(event){
		
		event.preventDefault(); 
		
		var targetPage = $(this).attr("href");
		
		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action","/board/listPage").attr("method", "get");
		jobForm.submit();
	});
	
</script>
 -->
<%@include file="../include/footer.jsp" %>
 
 
  