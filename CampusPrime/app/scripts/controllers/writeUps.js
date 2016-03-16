'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:WriteupCtrl
 * @description
 * # WriteupCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('WriteupCtrl', function($scope, $rootScope, $location, $http, AlertService, UserService ) {

		$rootScope.currentPage = 'Write-Ups';
		$scope.writeups = [];// write-up array object for showing the list, populate after fetching from server
		$scope.writeUp = {}; // write-up object for mapping models in popup
		$scope.writeupTypes = ['Literatur', 'Drawing', 'Pictures', 'Videos'];


		$scope.fetchWriteUps = function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetWriteUps'

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.writeups = JSON.parse(response.data.writeups);
			    };
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			  });
			
		}

		$scope.fetchWriteUps();

		$scope.uploadWriteUp = function() {
			var formData = new FormData();
			formData.append('file', $scope.writeUp.file);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/uploadFile',
			  headers: {'Content-Type': undefined},
			  data: formData

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.saveWriteUps(response.data.fileId);
			    };
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened while saving you file, Please try again later.");
			  });
		}

		$scope.saveWriteUps		= function(fileId){

			$scope.writeUp.fileId = fileId;
			$scope.writeUp.audienceId = 1;
			$scope.writeUp.isApproved = 1;
			$scope.writeUp.publishedBy = UserService.getUserId();
			$scope.writeUp.publishedDate = new Date().getTime();
			console.log($scope.writeUp);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/saveWriteUps',
			  data: $scope.writeUp

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#writeUpPopup').modal('hide') 
			    	AlertService.showAlert("Upload success!", "Succesfully uploaded the writeup");
			    	$scope.fetchWriteUps();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			    $scope.writeUp = {};
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}

		

	});