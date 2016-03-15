'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('LoginCtrl', function($scope, $location, $http, AlertService) {


		$scope.user 	= {};

		$scope.submit = function() {


			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/checkLogin',
			  data: $scope.user

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if(response.data.status === Constants.success){
			    	$location.path('/dashboard');
			    }else{
			    	AlertService.showAlert("Login Failed!", response.data.Message);
			    }
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Login Failed!", "Something wrong happened, Please try again later.");
			  });
		}
		

	});