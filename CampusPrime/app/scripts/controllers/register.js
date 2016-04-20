'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('RegisterCtrl', function($scope, $location, $http, AlertService, AudienceService) {

		$scope.selectedRole = 'Student';

		$scope.user			= {};

		$scope.yearClassList = [];

		$scope.register     = function () {
			console.log($scope.activeTab);
			if ($scope.user.student) {
				$scope.user.isTeacher =false;
			}
			else {
				$scope.user.isTeacher =true;
				$scope.user.year = 'all';
				$scope.user.classOrSRoom = 'all';
			}
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

		AudienceService.getYearAncClasses(
            function(yearClassList) {
                $scope.yearClassList = yearClassList;
            }, 
            function(error) {
                AlertService.showAlert("Campus Prime", error + "\n Please try  again later.");
            }
        );
        
        $scope.getDisplayYear = function(year) {
            return year +' - '+ (parseInt(year)+4);
        }
	});