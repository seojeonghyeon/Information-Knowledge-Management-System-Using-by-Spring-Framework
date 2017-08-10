<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp" %>
	
    <!-- Main content -->
    <section class="content">
      <div class="row">
      <!-- left column -->
      <div class="col-md-12">
        <!-- general form elements -->

		<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="/js/jquery-1.7.2.min.js" charset="UTF-8"></script> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
	<style>
		.selectbox { 
		position: relative; width: 200px;
		border: 1px solid #999;
		z-index: 1; 
		} 
		.selectbox:before { 
		content: ""; 
		position: absolute; 
		top: 50%; 
		right: 15px; 
		width: 0; 
		height: 0; 
		margin-top: -1px; 
		border-left: 5px solid transparent; 
		border-right: 5px solid transparent; 
		border-top: 5px solid #333; 
		} 
		.selectbox label { 
		position: absolute; 
		top: 1px; 
		left: 5px;
		padding: .8em .5em; 
		color: #999; 
		z-index: -1;
		} .selectbox select { 
		width: 100%; 
		height: auto; 
		line-height: normal;
		font-family: inherit;
		padding: .8em .5em;  
		border: 0; opacity: 0; 
		filter:alpha(opacity=0);
		-webkit-appearance: none;
		-moz-appearance: none; 
		appearance: none; 
		}
	</style>
	<script type="text/javascript">
		function myFunction() {
		    var qr = document.getElementById("QR");
		    if(qr.value == '1'){  //도서
		    	document.getElementById("book").style.display="block";
		    	document.getElementById("food").style.display="none";
		    }else if(qr.value == '2'){  //식료품
		    	document.getElementById("food").style.display="block";
		    	document.getElementById("book").style.display="none";
		    }else if(qr.value == '9'){  //default
		    	document.getElementById("book").style.display="none";
		    	document.getElementById("food").style.display="none";
		    }
		}
		$(document).ready(function(){
			$("#execute").click(function(){
				var qr = document.getElementById("QR");
				googleQRUrl = "https://chart.googleapis.com/chart?chs=177x177&cht=qr&chl=";
				if(qr.value == 1){  //도서
					var bookname = $("#bookname").val(); 
					var writer = $("#writer").val();
					var pagenumber = $("#pagenumber").val(); 
					bookname = encodeURIComponent(bookname);
					pagenumber = encodeURIComponent(pagenumber);
					writer = encodeURIComponent(writer);
					googleQRUrl = "https://chart.googleapis.com/chart?chs=177x177&cht=qr&chl=";
					var content = "도서/"+bookname+"/"+pagenumber+"/"+writer;
					$("#img").attr("src", googleQRUrl + "content="+content+'&choe=UTF-8');
					
				}else if(qr.value == 2){ //식료품
					var foodname = $("#foodname").val();
					var maker = $("#maker").val();
					var foodday = $("#foodday").val();
					foodname = encodeURIComponent(foodname);
					maker = encodeURIComponent(maker);
					foodday = encodeURIComponent(foodday);
					var content = "식료품/"+foodname+"/"+maker+"/"+foodday;
				}
				$("#img").attr("src", googleQRUrl + "content="+content+'&choe=UTF-8');
				});});
	</script>
<div>
	<img id="img" style="display:none; float:right;" onload="this.style.display='block'" />
	<form name="qrcode_form" method="post">
		QR.ver: 3.0<input type="hidden" name="qr_version" value="3.0" size="5" style="display:none"/>
		<br>
		<select name="QR" id="QR" onchange="myFunction()">
		    <option value="9" selected>항목 선택</option>
		    <option value="1">도서</option>
		    <option value="2">식료품</option>
		</select>
		<!-- 도서 -->
		<div name="book" id="book" style="display:none">
			도서 명 : <input type="text" id="bookname" name="bookname"/><br>
			저자 명 : <input type="text" id="writer" name="writer"/><br>
			페이지 수 : <input type="text" id="pagenumber" name="pagenumber"/><br>
		</div>
		
		<!-- 식료품 -->
		<div name="food" id="food" style="display:none">
			식품 명 : <input type="text" id="foodname" name="foodname"/><br>
			제조업체 명 : <input type="text" id="maker" name="maker"/><br>
			유통기한 : <input type="text" id="foodday" name="foodday"/><br>
		</div>
		<input type="button" id="execute" value="Generate"/>
	</form>
</div>
</body>
</html>
             
        </div>
      </div><!--/.col (left) -->
      </div>   <!-- /.row -->
    </section><!-- /.content -->

    
<%@include file="../include/footer.jsp" %>
 
 
  