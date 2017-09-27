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

<form role="form" method="post">

	<div class="box-body">

		

		<div class="form-group">
			<label for="exampleInputEmail1">정보 확인을 위해 로그인해주세요</label> <br/>ID<input type="text"
				name='title'   maxlength="30">&nbsp; &nbsp;
				PW<input type="password"
				name='title'  maxlength="30">
		</div>
	</div>
	<!-- /.box-body -->
</form>
<div class="box-footer">
	<button type="submit" class="btn btn-primary">Login</button>
	<button type="submit" class="btn btn-warning">CANCEL</button>
</div>

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/main/main";
		});

		$(".btn-primary").on("click", function() {
			self.location = "modifyuser2"
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
