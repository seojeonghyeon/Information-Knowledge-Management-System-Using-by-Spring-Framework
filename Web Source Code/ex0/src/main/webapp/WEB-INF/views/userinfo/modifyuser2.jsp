<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h2 class="box-title">정보 수정</h2>
				</div>
				
				<!-- /.box-header -->

 <form method="post" action="http://yunghelor.cafe24.com/modelview/user_save.php" onsubmit="return checkValue()">
            <table>
                
                <tr>
                    <td id="title">PW</td>
                    <td>
                        <input type="password" name=passwd maxlength="50">
                    </td>
                </tr>
                
                <tr>
                    <td id="title">RePass</td>
                    <td>
                        <input type="password" name=passwordcheck maxlength="50">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">Name</td>
                    <td>
                        <input type="text" name=username maxlength="50">
                    </td>
                </tr>
   
                <tr>
                    <td id="title">E-mail</td>
                    <td>
                        <input type="text" name=mail maxlength="50">
                      
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">Phone</td>
                    <td>
                        <input type="text" name=phone />
                    </td>
                </tr>
                <tr>
                    <td id="title">Address</td>
                    <td>
                        <input type="text" size="20" name=address />
                    </td>
                </tr>
            </table>
<div class="box-footer">
	<button type="submit" class="btn btn-primary">수정</button>
	<button type="submit" class="btn btn-warning">취소</button>
</div>

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/main/main";
		});

		$(".btn-primary").on("click", function() {
			self.location = "userinfo/modifyuser2.jsp"
		});

	});
</script>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
