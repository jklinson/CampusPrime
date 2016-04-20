'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:UserCntrl
 * @description
 * # UserCntrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('UserCntrl', function($scope, $location, $http, AlertService, AudienceService, UserService) {

		$scope.users = [];
        $scope.filterArray = [
            {name:'Student', filterTag: {isTeacher : false}},
            {name:'Teacher', filterTag: {isTeacher : true}},
            {name:'Admin', filterTag: {isAdmin : true}}
        ];
        $scope.filterVal = $scope.filterArray[0];
        $scope.yearClassList = [];
        
        $scope.fetchUsers = function() {

            $http({
              method: 'GET',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetUsers'

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                
                    $scope.users = response.data;
                    var user = UserService.getUser();
                    $scope.users = $scope.users.filter(function(eachUser){
                        if(eachUser.userId === user.userId)
                            return false;
                        else if(!user.isTeacher)
                            return (!eachUser.isTeacher && eachUser.classOrSRoom === user.adminOfClass && eachUser.year === user.adminOfYear);
                        else if(user.email == 'admin@gmail.com')
                            return true;
                        else
                            return (eachUser.classOrSRoom === user.adminOfClass && eachUser.year === user.adminOfYear);
                    });
                    
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Campus Prime!", "Something wrong happened, Please try again later.");
              });
            
        }
        
        $scope.updateUser = function(user) {
            
            $http({
              method: 'POST',
              data:user,
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/UpdateUser'

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                 if (response.data.status === Constants.success ) {
                     
                     $scope.fetchUsers();
                     AlertService.showAlert("Campus Prime!", response.data.message);
                 }
                 else{
                     AlertService.showAlert("Campus Prime!", "Unable to update the user.");
                 }
                 
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Campus Prime!", "Something wrong happened, Please try again later.");
              });
            
        }
        
        $scope.fetchUsers();
        
        $scope.showBatchPopup = function(user){
            
            $scope.editUser = user;
            $('#batchPopup').modal('show');    
        }
        
        $scope.updateAdmin = function(isDelete){
            var url = "";
            var selectedAudience = $scope.yearClassList.filter(function(yearClass){
                return (yearClass.year ===$scope.editUser.adminOfYear && 
                yearClass.classNum === $scope.editUser.adminOfClass);
            });
            
            $scope.editUser.adminTargetId = selectedAudience[0].targetId;
            
            if($scope.editUser.isAdmin && !isDelete){
                url = "http://localhost:8080/RESTfulProject/REST/WebService/updateAdmin";
            }
            else if(!$scope.editUser.isAdmin && !isDelete){
                url = "http://localhost:8080/RESTfulProject/REST/WebService/saveAdmin";
            }else{
                url = "http://localhost:8080/RESTfulProject/REST/WebService/deleteAdmin";
            }
            $http({
              method: 'POST',
              data:$scope.editUser,
              url: url

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                     $('#batchPopup').modal('hide');
                 if (response.data.status === Constants.success ) {
                     
                     $scope.fetchUsers();
                     AlertService.showAlert("Campus Prime!", response.data.message);
                 }
                 else{
                     AlertService.showAlert("Campus Prime!", "Unable to update the admin power for user.");
                 }
                 
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                $('#batchPopup').modal('hide');
                AlertService.showAlert("Campus Prime!", "Something wrong happened, Please try again later.");
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
            if(year ==='all') return year;
            return year +' - '+ (parseInt(year)+4);
        }
        
	}); 