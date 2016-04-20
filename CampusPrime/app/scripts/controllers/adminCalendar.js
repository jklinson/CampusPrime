'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:CalendarAdminCntrl
 * @description
 * # CalendarAdminCntrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('CalendarAdminCntrl', function($scope, $location, $http, AlertService, AudienceService, UserService) {
        $scope.events = [];
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
        
		$scope.getDisplayEventDate = function (startsAt, endsAt) {
			var startDate  =  new Date(parseInt(startsAt));
			var endDate    =  new Date(parseInt(endsAt));
			var startDateString  = startDate.getDate()+"-"+(startDate.getMonth()+1)+"-"+startDate.getFullYear()+" "+
								startDate.getHours()+"."+startDate.getMinutes();
			var endDateString  = endDate.getDate()+"-"+(endDate.getMonth()+1)+"-"+endDate.getFullYear()+" "+
								endDate.getHours()+"."+endDate.getMinutes();
			return startDateString +"   To  " +endDateString;
		}
    $scope.fetchEvents = function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetCalendarEvents'

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    //consolle.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.events = JSON.parse(response.data.calendarEvents);
			    };
			    var user = UserService.getUser();
                $scope.events = $scope.events.filter(function(event){
					if(!user.isTeacher)
						return (!event.isTeacher && event.year === user.adminOfYear && event.classNum === user.adminOfClass);
					else if(user.year == 'all')
						return true;
					else
					    return (event.year === user.adminOfYear && event.classNum === user.adminOfClass);
                });
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    //consolle.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			  });
			
		}

		$scope.fetchEvents();
        
        
        
			$scope.deleteEvent		= function(deleteItem){

				//consolle.log(deleteItem);
				$http({
					method: 'POST',
					url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteCalendarEvents',
					data: deleteItem

				}).then(function successCallback(response) {
					//consolle.log('In successCallback '+JSON.stringify(response));
					if (response.data.status === Constants.success ) {
						AlertService.showAlert("Campus Prime!", "Succesfully deleted the notification");
						$scope.fetchEvents();
					}
					else{
						AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
					}

				}, function errorCallback(response) {
					//consolle.log('In errorCallback '+JSON.stringify(response));

				});

			}
        
			$scope.updateEvent          = function(notification){           


				$http({
					method: 'POST',
					url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateCalendarEvents',
					data: notification

				}).then(function successCallback(response) {
					//consolle.log('In successCallback '+JSON.stringify(response));
					if (response.data.status === Constants.success ) {
						AlertService.showAlert("Campus Prime!", "Succesfully approved the Notification");
						$scope.fetchEvents();
					}
					else{
						AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
					}

				}, function errorCallback(response) {
					//consolle.log('In errorCallback '+JSON.stringify(response));

				});
			}
    });