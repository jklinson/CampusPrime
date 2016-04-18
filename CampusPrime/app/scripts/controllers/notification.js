'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:CalendarCtrl
 * @description
 * # NotificationCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('NotificationCtrl', function($scope, $rootScope, $location, $http, AlertService, UserService, AudienceService) {

		$rootScope.currentPage = 'Notificaitons';
		$scope.notifications = [];// notification array object for showing the list, populate after fetching from server
		$scope.notification = {}; // notification object for mapping models in popup
        $scope.editingItem = {};
        $scope.isEditing = false;
        $scope.yearClassList = [];
        
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
            // $scope.notification.isTeacher= $scope.notification.isTeacher? 1:0;
            if($scope.notification.allowAll === 1){
                $scope.notification.year = 'all';
                $scope.notification.classNum = 'all';
                $scope.notification.isTeacher = 1;
            }
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
        
        $scope.deleteNotifications		= function(deleteItem){
			
			console.log(deleteItem);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteNotifications',
			  data: deleteItem

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	AlertService.showAlert("Campus Prime!", "Succesfully deleted the notifications");
			    	$scope.fetchNotifications();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}
        
        $scope.editNotification          = function(){
            if($scope.notification.allowAll == 1){
                $scope.notification.year = 'all';
                $scope.notification.classNum = 'all';
                $scope.notification.isTeacher = 1;
            }
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateNotifications',
			  data: $scope.notification

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#notificationPopup').modal('hide') 
			    	AlertService.showAlert("Campus Prime!", "Succesfully updated the notification");
			    	$scope.fetchNotifications();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });
        }
        $scope.updateNotificaation		= function(updateItem){
			
            $scope.notification = angular.copy(updateItem);
            $scope.editingItem = angular.copy(updateItem);
            $scope.isEditing = true;
			console.log(updateItem);
            
            $('#notificationPopup').modal('show');
			

		}
        
        $('#notificationPopup').on('hidden.bs.modal', function (e) {
            console.log('hidden event called');
            if($scope.isEditing){
                $scope.notification = {};
                $scope.editingItem = {};
                $scope.isEditing = false;                
            }
            $scope.notification.file = {};
            $scope.$apply();
            
        });
        
        
        $scope.doDisplay = function(item){
            if(item.publishedBy === UserService.getUserId()){
                return true;
            }
            return false;
        }
        
         $('#notificationPopup').on('show.bs.modal', function (e) {
             if(!$scope.isEditing){
                $scope.notification = {};       
                $scope.notification.file = {};                
                $scope.notification.allowAll = 1;
                $scope.$apply();                 
             }
            else{
                if($scope.notification.year === 'all'){
                    $scope.notification.allowAll = 1;
                }
                else{
                    $scope.notification.allowAll = 0;
                }
            }
        }); 
        
        AudienceService.getYearAncClasses(
            function(yearClassList) {
                $scope.yearClassList = yearClassList;
            }, 
            function(error) {
                AlertService.showAlert("Campus Prime", error + "\n Please try  again later.");
            }
        );
        $scope.getDisplayYear = function(year) {
			if(year ==='all') return year;
            return year +' - '+ (parseInt(year)+4);
        }
        
	});