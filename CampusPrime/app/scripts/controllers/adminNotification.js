'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:NotificationAdminCntrl
 * @description
 * # NotificationAdminCntrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('NotificationAdminCntrl', function($scope, $location, $http, AlertService, AudienceService, UserService) {
        $scope.notifications = [];
        $scope.filterArray = [
            {name:'All', filterTag: {}},
            {name:'Approved', filterTag: {isApproved : 1}},
            {name:'Disapproved', filterTag: {isApproved : 0}}
        ];
        $scope.filterVal = $scope.filterArray[0];
        
        $scope.getFormatedDate      = function(newsDate){
            var date = new Date(parseInt(newsDate));
            return date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear();
        }
        
    $scope.fetchNotifications = function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetNotifications'

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    //console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.notifications = JSON.parse(response.data.notifications);
			    };
			    var user = UserService.getUser();
					$scope.notifications = $scope.notifications.filter(function(not){
						if(!user.isTeacher)
							return (!not.isTeacher && news.year === user.adminOfYear && not.classNum === user.adminOfClass);
						else if(user.email == 'admin@gmail.com')
							return true;
						else
							return (not.year === user.adminOfYear && not.classNum === user.adminOfClass);
					});
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    //console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			  });
			
		}

		$scope.fetchNotifications();
        
        
        
			$scope.deleteNotification		= function(deleteItem){

				//console.log(deleteItem);
				$http({
					method: 'POST',
					url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteNotifications',
					data: deleteItem

				}).then(function successCallback(response) {
					//console.log('In successCallback '+JSON.stringify(response));
					if (response.data.status === Constants.success ) {
						AlertService.showAlert("Campus Prime!", "Succesfully deleted the notification");
						$scope.fetchNotifications();
					}
					else{
						AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
					}

				}, function errorCallback(response) {
					//console.log('In errorCallback '+JSON.stringify(response));

				});

			}
        
			$scope.updateNotification          = function(notification){           


				$http({
					method: 'POST',
					url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateNotifications',
					data: notification

				}).then(function successCallback(response) {
					//console.log('In successCallback '+JSON.stringify(response));
					if (response.data.status === Constants.success ) {
						AlertService.showAlert("Campus Prime!", "Succesfully approved the Notification");
						$scope.fetchNotifications();
					}
					else{
						AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
					}

				}, function errorCallback(response) {
					//console.log('In errorCallback '+JSON.stringify(response));

				});
			}
    });