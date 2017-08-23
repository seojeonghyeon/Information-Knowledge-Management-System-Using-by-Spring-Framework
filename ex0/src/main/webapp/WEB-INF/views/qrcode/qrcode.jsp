<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp" %>
	
    <!-- Main content -->
    <section class="content">
      <div class="row">
      <!-- left column -->
      <div class="col-md-12">
        <!-- general form elements -->

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script  type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js" charset="UTF-8"></script> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

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
	
	<script type="text/javascript" content="text/html; charset=utf-8">
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
				var content = "";
				var name = "";
				var maker="";
				var encodeContent ="";
				googleQRUrl = "https://chart.googleapis.com/chart?chs=177x177&cht=qr&chl=";
				if(qr.value == 1){  //도서
					name = $("#bookname").val(); 
					maker = $("#writer").val();
					var pagenumber = $("#pagenumber").val(); 
					content = "도서/"+name+"/"+maker+"/"+pagenumber;
					
					
				}else if(qr.value == 2){ //식료품
					name = $("#foodname").val();
					maker = $("#maker").val();
					var foodday = $("#foodday").val();
					content = "식료품/"+name+"/"+maker+"/"+foodday;
					
				}
				encodeContent = encodeURIComponent(content);
				$("#img").attr("src", googleQRUrl + "content="+encodeContent+'&choe=UTF-8');
				$("#asset_class").attr("value", qr.value);
				$("#asset_number").attr("value", name);
				$("#Maker").attr("value", maker);
				$("#asset_info").attr("value", content);
				$("#qr_image").attr("value", googleQRUrl + "content="+encodeContent+'&choe=UTF-8');
			});
			}
		);
	</script>
<div>
	<form action="qr_saveService" id="qr_saveService" method="post">
		<img id="img" style="display:none; float:right;" onload="this.style.display='block'" />
		<input type="text" name="asset_class" id="asset_class" style="display:none"/>
		<input type="text" name="asset_number" id="asset_number"  style="display:none"/>
		<input type="text" name="Maker" id="Maker" style="display:none"/>
		<input type="text" name="asset_info" id="asset_info" style="display:none"/>
		<input type="text" name="qr_image" id="qr_image" style="display:none"/>
		
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
		<div>
			<input type="button" id="execute" value="Generate"/>
			<input type="submit" id="qr_save" value="save"/>
		</div>
	</form>
</div>
             
        </div>
      </div><!--/.col (left) -->
      </div>   <!-- /.row -->
    </section><!-- /.content -->

    
<%@include file="../include/footer.jsp" %>
 
 
  