<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<body>

<div ng-app="myApp" ng-controller="myCtrl"> 

<p>Today's welcome message is:</p>
<p ng-model="title" name="title">9876</p>
<p ng-model="copy" name="copy">angular</p>

<h1>{{paste}}</h1>

</div>

<p>The $http service requests a page on the server, and the response is set as the value of the "myWelcome" variable.</p>

<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
  $http.post("ngpaste.php",{'title':$scope.title, 'copy':$scope.copy})
  .success(function(response) {
      $scope.paste = "done";
  });
});
</script>

</body>
</html>
