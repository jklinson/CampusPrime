'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:CalendarCtrl
 * @description
 * # CalendarCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('CalendarCtrl', function($scope, $rootScope, $location, moment, UserService, $http, AlertService, AudienceService) {

		$rootScope.currentPage = 'Calendar';
		$scope.calendarView = 'month';
	    $scope.viewDate = new Date();
	    $scope.events = [];
        $scope.editingItem = {};
        $scope.isEditing = false;
	    $scope.isCellOpen = true;
        $scope.yearClassList = [];

	    $scope.eventClicked = function(event) {
	      alert('Clicked', event);
	    };

	    $scope.eventEdited = function(event) {
	      //alert('Edited', event);
          console.log(event);
           $scope.event = angular.copy(event);
            $scope.editingItem = angular.copy(event);
            $scope.isEditing = true;
            
            $('#calendarPopup').modal('show');
	    };

	    $scope.eventDeleted = function(event) {
	      //alert('Deleted', event);
          console.log(event);
          $scope.deleteCalendarEvents(event);
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
                    var user = UserService.getUser();
                    $scope.events = $scope.events.filter(function(event){
                        if(user.isTeacher){
                            return (event.isApproved ===1 && (event.isTeacher || event.publishedBy === user.userId 
                            || event.audienceId  ===17));
                        }
                            return ((event.isApproved ===1) && ((event.classNum === user.adminOfClass && 
                            event.year === user.adminOfYear) || event.audienceId  ===17));
                    });
                    angular.forEach($scope.events, function(value, key) {
					  value.startsAt = new Date(parseInt(value.startsAt));
					  value.endsAt = new Date(parseInt(value.endsAt));
					  value.editable = UserService.getUserId() === value.publishedBy ? true: false;
					  value.deletable = UserService.getUserId() === value.publishedBy ? true: false;
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
           
            $scope.event.publishedBy 	= UserService.getUserId();
            $scope.event.publishedDate 	= new Date().getTime();
            $scope.event.startsAt   	= $scope.event.startsAt.getTime();
            $scope.event.endsAt   		= $scope.event.endsAt.getTime();
            $scope.event.isApproved     = 0;
            // $scope.event.isTeacher      = $scope.event.isTeacher? 1:0;
            if($scope.event.allowAll === 1){
                $scope.event.year = 'all';
                $scope.event.classNum = 'all';
                $scope.event.isTeacher = 1;
            }
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
        
         $scope.deleteCalendarEvents		= function(deleteItem){
			
			console.log(deleteItem);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteCalendarEvents',
			  data: deleteItem

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	AlertService.showAlert("Campus Prime!", "Succesfully deleted the event");
			    	$scope.fetchCalendarEvents();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  }); 

		}
        
        $scope.editCalendarEvents          = function(){
            
            var data = angular.copy($scope.event);
            data.startsAt   	= data.startsAt.getTime();
            data.endsAt   		= data.endsAt.getTime();
            if(data.allowAll == 1){
                data.year = 'all';
                data.classNum = 'all';
                data.isTeacher = 1;
            }
            
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateCalendarEvents',
			  data: data

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#calendarPopup').modal('hide') 
			    	AlertService.showAlert("Campus Prime!", "Succesfully updated the event");
			    	$scope.fetchCalendarEvents();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });
        }
        
        
        $('#calendarPopup').on('hidden.bs.modal', function (e) {
            console.log('hidden event called');
            if($scope.isEditing){
                $scope.event = {};
                $scope.editingItem = {};
                $scope.isEditing = false;
                $scope.$apply();       
            }     
        });
        
        $('#calendarPopup').on('show.bs.modal', function (e) {
            if(!$scope.isEditing){
                $scope.event = {};       
                $scope.event.file = {};                
                $scope.event.allowAll = 1; 
                $scope.event.isTeacher =1;
                $scope.$apply();
            }
            else{
                if($scope.event.year === 'all'){
                    $scope.event.allowAll = 1;
                }
                else{
                    $scope.event.allowAll = 0;
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