<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<html>
  <head>
    <meta charset="UTF-8">
    <title>IKAMS</title>
    
    <!-- Import Other -->
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>

	<!-- Import need extra -->

	
  </head>
<body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href=""><b>IKAMS</b>Project</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">로그인해주세요</p>
    <!-- Main content -->
    <section class="content">
     <div>
     	<form action="main" method="POST" id="loginProcess">
     		  <table>
                <tr>
                    <td id="title">ID</td>
                    <td>
                        <input type="text" name=user_ID maxlength="50">
                         
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">PW</td>
                    <td>
                        <input type="password" name=user_Password maxlength="50">
                    </td>
                </tr>

                </table>
             <input type="submit" value="Log-in"/>
     	</form>
     
     </div>
    </section>

  </body>
</html>

    

 
 
  