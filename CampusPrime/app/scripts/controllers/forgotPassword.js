'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('ForgotPaswrdCntrl', function($scope, $location, $http, AlertService, UserService) {
    
    $scope.email = "";
    
    
    $scope.checkAndSendMail =  function() {
        if($scope.email.length >0){
            $http({
              method: 'GET',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/CheckAndSendMail/'+$scope.email

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                if(response.data.status === Constants.success ) {
                    AlertService.showAlert("Success!", response.data.message);
                    $location.path('/login');
                }
                else{
                    AlertService.showAlert("Failure", response.data.message);
                }
                
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Campus Prime!", "Something wrong happened, Please try again later.");
              });
        }
    }  


});