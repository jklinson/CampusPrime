'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:CalendarCtrl
 * @description
 * # CalendarCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('CalendarCtrl', function($scope, $rootScope, $location, moment, UserService, $http, AlertService) {

		$rootScope.currentPage = 'Calendar';
		$scope.calendarView = 'month';
	    $scope.viewDate = new Date();
	    $scope.events = [];

	    $scope.isCellOpen = true;

	    $scope.eventClicked = function(event) {
	      alert('Clicked', event);
	    };

	    $scope.eventEdited = function(event) {
	      alert('Edited', event);
	    };

	    $scope.eventDeleted = function(event) {
	      alert('Deleted', event);
	    };

	    $scope.eventTimesChanged = function(event) {
	      alert('Dropped or resized', event);
	    };

	    $scope.toggle = function($event, field, event) {
	      $event.preventDefault();
	      $event.stopPropagation();
	      event[field] = !event[field];
	    };


        $scope.fetchCalendarEvents = function() {

            $http({
              method: 'GET',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetCalendarEvents'

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                if (response.data.status === Constants.success ) {
                    $scope.events = JSON.parse(response.data.calendarEvents);
                    console.log($scope.events);
                    angular.forEach($scope.events, function(value, key) {
					  value.startsAt = new Date(parseInt(value.startsAt));
					  value.endsAt = new Date(parseInt(value.endsAt));
					  value.editable = false;
					  value.deletable = false;
					});
                };
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
              });
            
        }


        $scope.saveCalendarEvents    = function(fileId){

            $scope.event.audienceId 	= 1;
            $scope.event.isApproved 	= 1;
            $scope.event.publishedBy 	= UserService.getUserId();
            $scope.event.publishedDate 	= new Date().getTime();
            $scope.event.startsAt   	= $scope.event.startsAt.getTime();
            $scope.event.endsAt   		= $scope.event.endsAt.getTime();
            console.log($scope.event);
            $http({
              method: 'POST',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/saveCalendarEvents',
              data: $scope.event

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                if (response.data.status === Constants.success ) {
                    
                    AlertService.showAlert("Upload success!", response.data.message);
                    $scope.fetchCalendarEvents();
                }
                else{
                    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
                }
                $('#calendarPopup').modal('hide');
                $scope.event = {};
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.event = {};
                $('#calendarPopup').modal('hide');
                AlertService.showAlert("Upload Failed!", response.data.message);
                console.log('In errorCallback '+JSON.stringify(response));
                
              });

        }

        $scope.fetchCalendarEvents();

        $scope.getFormatedDate      = function(newsDate){
            var date = new Date(parseInt(newsDate));
            return date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear();
        }

	});