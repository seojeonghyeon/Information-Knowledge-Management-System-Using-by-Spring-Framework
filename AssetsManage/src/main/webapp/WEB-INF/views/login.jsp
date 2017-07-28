<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="resources/libs/jquery/src/jquery.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Manage</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
<link href="resources/css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body ng-app="finalApp" ng-controller="loginCtrl">
<div id="wrapper1">
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
		<img src="resources/images/Mark.png"  style="width="100px" height="100px"" alt="merkery_logo" class="visible-xs visible-sm circle-logo">
				<h1><a href="index.html">{{topic}}</a></h1>
				<span>Managing <a href="login.html" rel="nofollow">your assets</a></span> </div>
			<div id="menu">
				<ul>
					<li class="current_page_item"><a href="login.html" accesskey="1" title="">{{login}}</a></li>
					<li><a href="findPass.html" accesskey="3" title="">{{findPass}}</a></li>
				</ul>
			</div>
		</div>
	</div>

		<div id="wrapper2">
		<div id="welcome" class="container">
			<div class="title">
				<h2>Login</h2>
			</div>
			<center>
			<form name="loginform" method="post" action="loginProcess.php">
				<table>
					<thead>
						<tr>
							<th>Email : </th>
							<th><input type="text" name="userEmail" id="userEmail" placeholder="Enter the Email!" ></th>
						</tr>
						<tr>
							<th>Password : </th>
							<th><input type="Password" name="user_Password" id="user_Password" placeholder="Enter the Password!" ></th>
						</tr>
					</thead>
				</table>
				<input type=submit class="button alt" value="Login">
				</form>
			</center>
		</div>
	</div>
	</div>
</body>
</html>
