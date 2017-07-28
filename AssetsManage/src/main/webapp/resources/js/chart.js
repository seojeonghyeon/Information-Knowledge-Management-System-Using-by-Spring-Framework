google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
	  
function drawChart($scope) 
{
    var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  1000,      400],
          ['2005',  1170,      460],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
}
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
   $http.get("test.php")
   .then(function (response) {$scope.names = response.data.records;});
});
function change() {
    var scope = angular.element($("#outer")).scope();
    scope.$apply(function(){
        scope.msg = 'Superhero';
    })
}