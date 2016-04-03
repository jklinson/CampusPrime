'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:NewsCtrl
 * @description
 * # NewsCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
    .controller('NewsCtrl', function($scope, $rootScope, $location, $http, AlertService, UserService, $filter) {

        $rootScope.currentPage = 'News';
        $scope.myInterval = 5000;
        $scope.newses = [];
        $scope.newsFilterTag = {'isApproved' :1};
        $scope.fetchNews = function() {

            $http({
              method: 'GET',
              url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetNews'

            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                console.log('In successCallback '+JSON.stringify(response));
                if (response.data.status === Constants.success ) {
                    $scope.newses = JSON.parse(response.data.news);
                };
                
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
                };
                
              }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log('In errorCallback '+JSON.stringify(response));
                AlertService.showAlert("Upload Failed!", "Something wrong happened while saving you file, Please try again later.");
              });
        }

        $scope.saveNews     = function(fileId){

            $scope.news.fileId = fileId;
            $scope.news.audienceId = 1;
            $scope.news.isApproved = 1;
            $scope.news.publishedBy = UserService.getUserId();
            $scope.news.publishedDate = new Date().getTime();
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
                    $('#newsPopup').modal('hide') 
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
        
        $scope.getDisplayYear = function(year) {
            return UserService.getUserBatch();
        }
        
        $scope.fetchNewsByUser = function(){
            $scope.newsFilterTag = {'publishedBy' : UserService.getUserId()};
            console.log($scope.newsFilterTag);
            console.log($filter('filter')( $scope.newses, $scope.newsFilterTag));
        }
        $scope.fetchAllNewses = function(){
            $scope.newsFilterTag = {'isApproved' :1};
            console.log($scope.newsFilterTag);
            console.log($filter('filter')( $scope.newses, $scope.newsFilterTag));
        }
        $scope.fetchPending = function(){
            $scope.newsFilterTag = {'isApproved' :0};
            console.log($scope.newsFilterTag);
            console.log($filter('filter')( $scope.newses, $scope.newsFilterTag));
        }
        $scope.fetchBatchNewses = function(){
            $scope.newsFilterTag = {'year' :UserService.getUserYear()};
            console.log($scope.newsFilterTag);
            console.log($filter('filter')( $scope.newses, $scope.newsFilterTag));
        }
        $scope.fetchClassNews = function(){
            $scope.newsFilterTag = {'year' :UserService.getUserYear(), classNum : UserService.getUserClass()};
            console.log($scope.newsFilterTag);
            console.log($filter('filter')( $scope.newses, $scope.newsFilterTag));
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

    });