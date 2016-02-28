'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('RegisterCtrl', function($scope, $location, $http) {

		$scope.selectedRole = 'Student';

		$scope.user			= {};

		$scope.register     = function () {
			if ($scope.selectedRole === 'Student') $scope.user.isTeacher =0;
			else $scope.user.isTeacher =1;
			$scope.user.isActive =0;
			$scope.user.isEmailVerified =0;

			console.log($scope.user);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/register',
			  data: $scope.user

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if(response.data.Status === "Success"){
			    	$location.path('/login');
			    }else{
			    	alert(response.data.Message);
			    }
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			  });
		}

	});