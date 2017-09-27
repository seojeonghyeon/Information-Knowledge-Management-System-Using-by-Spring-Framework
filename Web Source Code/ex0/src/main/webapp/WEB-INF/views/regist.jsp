<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
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
<%
String user_Code = "";
user_Code = (String)session.getAttribute("user_Code");
%>
    <div class="login-box">
      <div class="login-logo">
        <a href="/resources/index2.html"><b>IKAMS</b>Project</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Register User</p>
    <!-- Main content -->
    <section class="content">
     <form method="GET" action="retu">
            <table>
                <tr>
                    <td id="title">ID</td>
                    <td>
                        <input type="text" name=user_ID maxlength="50">
                         <input type="button" name ="double" value="중복검사" >
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">PW</td>
                    <td>
                        <input type="password" name=user_Password maxlength="50">
                    </td>
                </tr>
                
                <tr>
                    <td id="title">RePass</td>
                    <td>
                        <input type="password" name=user_rePassword maxlength="50">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">Name</td>
                    <td>
                        <input type="text" name=user_Name maxlength="50">
                    </td>
                </tr>
                    
                    
                <tr>
                    <td id="title">E-mail</td>
                    <td>
                        <input type="text" name=user_Email maxlength="50">
                      
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">Phone</td>
                    <td>
                        <input type="text" name=user_Phone />
                    </td>
                </tr>
                <tr>
                    <td id="title">Address</td>
                    <td>
                        <input type="text" size="20" id=user_Address  name=user_Address />
                    </td>
                </tr>
                <tr>
                    <td id="title">Code</td>
                    <td>
                        <input type="text" size="20" id =user_Code name=user_Code value="<%=user_Code %>" disabled/>
                    </td>
                </tr>
            </table>
            <br>
         
            <input type="submit" value="가입"/>
        </form>
    </section><!-- /.content -->
      <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
  
  
  </body>
   
</html>

    

 
 
  