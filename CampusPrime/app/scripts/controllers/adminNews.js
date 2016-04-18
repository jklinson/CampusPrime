'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:NewsAdminCntrl
 * @description
 * # NewsAdminCntrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('NewsAdminCntrl', function($scope, $location, $http, AlertService, AudienceService, UserService) {
        $scope.newses = [];
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
        
        $scope.fetchNews = function() {

            $http({
              method: 'GET',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetNews'

            }).then(function successCallback(response) {
                
                if (response.data.status === Constants.success ) {
                    $scope.newses = JSON.parse(response.data.news);
                };
                var user = UserService.getUser();
                console.log($scope.newses);
                $scope.newses = $scope.newses.filter(function(news){
                    return (news.audienceId === user.adminTargetId);
                });
                console.log($scope.newses);
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
              });
            
        }
        $scope.fetchNews();
        
        $scope.showImage = function(fileId){
            console.log(fileId);
            $scope.fileId = fileId;
            $('#imagePopup').modal('show'); 
        }
        
         $scope.deleteNews		= function(deleteItem){
			
			console.log(deleteItem);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteNews',
			  data: deleteItem

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	AlertService.showAlert("Campus Prime!", "Succesfully deleted the news");
			    	$scope.fetchNews();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}
        
        $scope.updateNews          = function(news){           
            
            
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateNews',
			  data: news

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	AlertService.showAlert("Campus Prime!", "Succesfully approved the news");
			    	$scope.fetchNews();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });
        }
    });