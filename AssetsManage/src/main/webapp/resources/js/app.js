var finalApp = angular.module("finalApp", []);
finalApp.controller("ListCtrl", function($scope) {
    $scope.menuItems = [
		{Title : 'Main Page', Icon : 'fa fa-home', Link : 'home.html'},
		{Title : 'Assets Manage', Icon : 'fa fa-tasks', Link : 'workflow.html'},
		{Title : 'Assets Statistics', Icon : 'fa fa-bar-chart', Link : 'statistics.html'},
		{Title : 'Communicate', Icon : 'fa fa-user', Link : 'users.html'},
		{Title : 'User Page', Icon : 'fa fa-cog', Link : 'setting.html'}
	];
});
finalApp.controller("header1Ctrl",function($scope){
	
});
finalApp.controller("header2Ctrl",function($scope){
	$scope.user = 'User';
	$scope.notify = '1';
	$scope.doorInfo = 'Fingerprint&NFC';
	$scope.state = 'Safe :)';
	$scope.profile = 'View Profile';
});
finalApp.controller("dashboardCtrl",function($scope){
	
});
finalApp.controller("loginCtrl", function($scope) {
	$scope.topic = "Assets Manage";
    $scope.login = "Login";
    $scope.findPass = "Forgot your password?";
});
//jQuery not required. Used here for demonstrative purposes.
$(function() {
  $("#resizable").resizable({
    handles: 'ne, se, sw, nw'
  });
});
