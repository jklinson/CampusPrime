'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('RegisterCtrl', function($scope, $location, $http, AlertService) {

		$scope.selectedRole = 'Student';

		$scope.user			= {};

		$scope.yearClassList = [];

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
			    if(response.data.status === Constants.success){
			    	AlertService.showAlert("Campus Prime", response.data.message);
			    	$location.path('/login');
			    }else{
			    	AlertService.showAlert("Campus Prime", response.data.message);
			    }
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			  });
		}

		$scope.getYearAncClasses	= function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetYearAndClass'

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if(response.data.status === Constants.success){
			    	$scope.yearClassList = JSON.parse(response.data.yearClassList);
			    	console.log($scope.yearClassList);
			    	console.log(JSON.parse(response.data.yearClassList));
			    }else{
			    	AlertService.showAlert("Campus Prime", response.data.message + "\n Please try  again later.");
			    }
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			  });
		}

		$scope.getYearAncClasses();

	});