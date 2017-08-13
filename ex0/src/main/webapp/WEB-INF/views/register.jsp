<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>


<html>
  <head>
    <meta charset="UTF-8">
    <title>IKAMS</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    .login-box-body {
    height:400px;}
    </style>
  </head>
<body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="/resources/index2.html"><b>IKAMS</b>Project</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Register User</p>
    <!-- Main content -->
    <section class="content">
     <form method="post" action="registerProcess">
            <table>
                <tr>
                    <td id="title">ID</td>
                    <td>
                        <input type="text" name=id maxlength="50">
                         <input type="button" name ="double" value="중복검사" >
                    </td>
                </tr>
                        
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
            <br>
         
            <input type="submit" value="가입"/>  <input type="button" value="취소">
        </form>
    </section><!-- /.content -->
      <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
  
  
  </body>
   
</html>

    

 
 
  