<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="resources/libs/jquery/src/jquery.js"></script>
	<script type="text/javascript" src="resources/js/main.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Manage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/main.css" type="text/css" rel="stylesheet" />
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<!-- Importing about AngularJS && JQuery -->
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
	<script type="text/javascript" src="resources/js/app.js"></script>
</head>

<!--body-->
<body ng-app="finalApp">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">

<body class="home">
    <div class="container-fluid display-table">
        <div class="row display-table-row">
		<!--menu-->
            <div class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box" ng-controller="ListCtrl" id="navigation" ng-include="'resources/view/menu_forStatistics.html'">
            </div>
            <div class="col-md-10 col-sm-11 display-table-cell v-align">
                <!--<button type="button" class="slide-toggle">Slide Toggle</button> -->
                <div class="row">
				<!--header-->
                    <header>
                        <div class="col-md-7" ng-controller="header1Ctrl"">
						<nav class="navbar-default pull-left">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="offcanvas" data-target="#side-menu" aria-expanded="false">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									</button>
								</div>
							</nav>
						</div>
                        <div class="col-md-5" ng-controller="header2Ctrl" ng-include="'resources/view/header2.html'"></div>
                    </header>
                </div>
				<!--user_dashboard-->
                <div class="user-dashboard" ng-controller="dashboardCtrl" ng-include="'resources/dashboard/statistics.html'"></div>
            </div>
        </div>
    </div>
</body>
</body>
</html>