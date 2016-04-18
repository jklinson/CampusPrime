'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:WriteUpAdminCntrl
 * @description
 * # WriteUpAdminCntrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('WriteUpAdminCntrl', function($scope, $location, $http, AlertService, AudienceService, UserService) {
        $scope.writeUps = [];
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
        
        $scope.fetchWriteUps = function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetWriteUps/'+0

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    ////consollle.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.writeUps = JSON.parse(response.data.writeups);
			    };
			    var user = UserService.getUser();
                $scope.writeUps = $scope.writeUps.filter(function(writeUp){
                        return (writeUp.year === user.adminOfYear && writeUp.classNum === user.adminOfClass);
                });
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    ////consollle.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			  });
			
		}

		$scope.fetchWriteUps();
        
        $scope.showImage = function(fileId){
            ////consollle.log(fileId);
            $scope.fileId = fileId;
            $('#writeUpImagePopup').modal('show'); 
        }
        
         $scope.deleteWriteUp		= function(deleteItem){
			
			//consolle.log(deleteItem);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteWriteUps',
			  data: deleteItem

			}).then(function successCallback(response) {
			    //consolle.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	AlertService.showAlert("Campus Prime!", "Succesfully deleted the Writeups");
			    	$scope.fetchWriteUps();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    //consolle.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}
        
        $scope.updateWriteUps          = function(writeups){           
            
            
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateWriteUps',
			  data: writeups

			}).then(function successCallback(response) {
			    //consolle.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	AlertService.showAlert("Campus Prime!", "Succesfully approved the Writeups");
			    	$scope.fetchWriteUps();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    //consolle.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later. \n"+response);
			    
			  });
        }
    });