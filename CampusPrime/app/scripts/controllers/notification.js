'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:CalendarCtrl
 * @description
 * # NotificationCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('NotificationCtrl', function($scope, $rootScope, $location, $http, AlertService, UserService) {

		$rootScope.currentPage = 'Notificaitons';
		$scope.notifications = [];// write-up array object for showing the list, populate after fetching from server
		$scope.notification = {}; // write-up object for mapping models in popup


		$scope.fetchNotifications = function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetNotifications'

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.notifications = JSON.parse(response.data.notifications);
			    };
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			  });
			
		}

		$scope.fetchNotifications();

		$scope.uploadNotificationFile = function() {
			var formData = new FormData();
			formData.append('file', $scope.notification.file);
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
			    	$scope.saveNotifications(response.data.fileId);
			    };
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Failure!", "Something wrong happened while saving you file, Please try again later.");
			  });
		}

		$scope.saveNotifications		= function(fileId){

			$scope.notification.fileId = fileId;
			$scope.notification.audienceId = 1;
			$scope.notification.isApproved = 1;
			$scope.notification.publishedBy = UserService.getUserId();
			$scope.notification.publishedDate = new Date().getTime();
			console.log($scope.notification);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/saveNotifications',
			  data: $scope.notification

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#notificationPopup').modal('hide') 
			    	AlertService.showAlert("Upload success!", "Succesfully uploaded the notification");
			    	$scope.fetchNotifications();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			    $scope.notification = {};
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}
		$scope.getFormatedDate      = function(notificationDate){
            var date = new Date(parseInt(notificationDate));
            return date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear();
        }
	});