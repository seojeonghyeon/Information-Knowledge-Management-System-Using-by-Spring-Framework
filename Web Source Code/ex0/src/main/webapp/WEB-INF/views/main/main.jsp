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

					<div class="box">
            <div class="box-header with-border">
            	<h1>MAIN PAGE</h1>
            </div>
            <br/>
             <div class="box-header with-border">
            	<ul><h2>OUR FUNCTION</h2>
            		<li>QR Code 생성 및 배포</li>
            		<li>게시판</li>
            		<li>Mobile</li>
            		<li>NFC</li>
            	</ul>
            </div>
            
             <div class="box-header with-border">
            	<ul><h2>Team Member</h2>
            		<li>Mento : 차연철</li>
            		<li>Team Leader : ${sessionScope.user_ID}</li>
            		<li>Team Member : 서정현</li>
            	</ul>
            </div>
            <div class="box-header with-border">
            	<ul><h2>Communication</h2>
            		<li>Gmail  :  kmg08801@gmail.com</li>
            		<li>Facebook  :  facebook.com/nicedaykim</li>
            		<li>Instagram  :  taejo0ong</li>
            	</ul>
            </div>
          </div>
      </div><!--/.col (left) -->
      </div>   <!-- /.row -->
    </section><!-- /.content -->

    
<%@include file="../include/footer.jsp" %>
 
 
  