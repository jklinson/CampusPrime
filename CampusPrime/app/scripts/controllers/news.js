'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:NewsCtrl
 * @description
 * # NewsCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
    .controller('NewsCtrl', function($scope, $rootScope, $location, $http, AlertService, UserService, $filter, AudienceService) {

        $rootScope.currentPage = 'News';
        $scope.myInterval = 5000;
        $scope.newses = [];
        $scope.newsFilterTag = {'isApproved' :1};
        $scope.editingItem = {};
        $scope.isEditing = false;
        $scope.yearClassList = [];
        $scope.isTeacher = UserService.getIsTeacher();
        $scope.fetchNews = function() {

            $http({
              method: 'GET',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetNews'

            }).then(function successCallback(response) {
                if (response.data.status === Constants.success ) {
                    $scope.newses = JSON.parse(response.data.news);
                };
                var user = UserService.getUser();
                $scope.newses = $scope.newses.filter(function(news){
                    if(user.isTeacher)
                    {
                        return (news.isTeacher || news.publishedBy === user.userId || news.audienceId === user.adminTargetId || news.audienceId == 17);
                    }
                    return (news.year === user.adminOfYear || news.year === user.year
                    || news.publishedBy === user.userId || news.audienceId == 17);
                });
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
              });
            
        }

        $scope.uploadNews = function() {
            var formData = new FormData();
            formData.append('file', $scope.news.file);
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
                    $scope.saveNews(response.data.fileId);
                }else{
                    AlertService.showAlert("Upload Failed!", response.data.message);
                    $('#newsPopup').modal('hide');
                }
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Upload Failed!", "Something wrong happened while saving you file, Please try again later.");
              });
        }

        $scope.saveNews     = function(fileId){

            $scope.news.fileId = fileId;
            $scope.news.isApproved = 0;
            $scope.news.publishedBy = UserService.getUserId();
            $scope.news.publishedDate = new Date().getTime();
            // $scope.news.isTeacher= $scope.news.isTeacher? 1:0;
            if($scope.news.allowAll === 1){
                $scope.news.year = 'all';
                $scope.news.classNum = 'all';
                $scope.news.isTeacher = 1;
            }
            console.log($scope.news);
            $http({
              method: 'POST',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/saveNews',
              data: $scope.news

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                if (response.data.status === Constants.success ) {
                    $('#newsPopup').modal('hide'); 
                    AlertService.showAlert("Upload success!", "Succesfully uploaded the news");
                    $scope.fetchNews();
                }
                else{
                    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
                }
                
                $scope.news = {};
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                
              });

        }

        $scope.getFormatedDate      = function(newsDate){
            var date = new Date(parseInt(newsDate));
            return date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear();
        }
        
        $scope.getDisplayBatch = function() {
            return UserService.getUserBatch();
        }
        
        $scope.fetchNewsByUser = function(){
            $scope.newsFilterTag = {
                                        'publishedBy' : UserService.getUserId(),
                                        'isApproved' :1
                                    };
        }
        $scope.fetchAllNewses = function(){
            $scope.newsFilterTag = {'isApproved' :1};
        }
        $scope.fetchPending = function(){
            $scope.newsFilterTag = {'isApproved' :0};
        }
        $scope.fetchBatchNewses = function(){
            $scope.newsFilterTag = {'year' :UserService.getUserYear(),
                                        'isApproved' :1};
        }
        $scope.fetchClassNews = function(){
            $scope.newsFilterTag = {
                                        'year' :UserService.getUserYear(), 
                                        classNum : UserService.getUserClass(),
                                        'isApproved' :1
                                    };
        }
        
        $scope.getClass = function(value){
            if(value === 'all' && $scope.newsFilterTag.isApproved===1){
                return 'active';
            }
            else if(value === 'mine' && $scope.newsFilterTag.publishedBy ){
                return 'active';
            }
            else if(value === 'class' && $scope.newsFilterTag.classNum && $scope.newsFilterTag.year){
                return 'active';
            }
            else if(value === 'year' && $scope.newsFilterTag.year && !$scope.newsFilterTag.classNum){
                return 'active';
            }
            else if(value === 'pending' && ($scope.newsFilterTag.isApproved ===0) ){
                return 'active';
            }
            
            return '';
        }
        $scope.fetchNews();
        
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
        
        $scope.editNews          = function(){
            
            if($scope.news.allowAll == 1){
                $scope.news.year = 'all';
                $scope.news.classNum = 'all';
                $scope.news.isTeacher = 1;
            }
            
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateNews',
			  data: $scope.news

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#newsPopup').modal('hide') 
			    	AlertService.showAlert("Campus Prime!", "Succesfully updated the news");
			    	$scope.fetchNews();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });
        }
        $scope.updateNews		= function(updateItem){
			
            $scope.news = angular.copy(updateItem);
            $scope.editingItem = angular.copy(updateItem);
            $scope.isEditing = true;
			console.log(updateItem);
            
            $('#newsPopup').modal('show');
			

		}
        
        $('#newsPopup').on('hidden.bs.modal', function (e) {
            console.log('hidden event called');
            if($scope.isEditing){
                $scope.news = {};
                $scope.editingItem = {};
                $scope.isEditing = false;                
            }           
            $scope.news.file = {};
            $scope.$apply();     
        });
        
        $('#newsPopup').on('show.bs.modal', function (e) {
            if(!$scope.isEditing){
                $scope.news = {};       
                $scope.news.file = {};                
                $scope.news.allowAll = 1;
                $scope.news.isTeacher =1; 
                $scope.$apply();
            }
            else{
                if($scope.news.year === 'all'){
                    $scope.news.allowAll = 1;
                }
                else{
                    $scope.news.allowAll = 0;
                }
            }
        });       
        
        
        $scope.doDisplay = function(item){
            if(item.publishedBy === UserService.getUserId()){
                return true;
            }
            return false;
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