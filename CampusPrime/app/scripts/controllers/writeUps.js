'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:WriteupCtrl
 * @description
 * # WriteupCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('WriteupCtrl', function($scope, $rootScope, $location, $http, AlertService, UserService, AudienceService ) {

		$rootScope.currentPage = 'Write-Ups';
		$scope.writeups = [];// write-up array object for showing the list, populate after fetching from server
		$scope.writeUp = {}; // write-up object for mapping models in popup
		$scope.writeupTypes = ['Literatur', 'Drawing', 'Pictures', 'Videos'];
        $scope.filterTag = {'isApproved':1};
        $scope.userId = UserService.getUserId();
        $scope.editingItem = {};
        $scope.isEditing = false; 
        $scope.yearClassList = [];       
        
		$scope.fetchWriteUps = function() {

			$http({
			  method: 'GET',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetWriteUps/'+$scope.userId

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$scope.writeups = JSON.parse(response.data.writeups);
			    };
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			  });
			
		}

		$scope.fetchWriteUps();

		$scope.uploadWriteUp = function() {
			var formData = new FormData();
			formData.append('file', $scope.writeUp.file);
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
			    	$scope.saveWriteUps(response.data.fileId);
			    };
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    AlertService.showAlert("Upload Failed!", "Something wrong happened while saving you file, Please try again later.");
			  });
		}

		$scope.saveWriteUps		= function(fileId){

			$scope.writeUp.fileId = fileId;
			$scope.writeUp.isApproved = 0;
			$scope.writeUp.publishedBy = UserService.getUserId();
			$scope.writeUp.publishedDate = new Date().getTime();
            // $scope.writeUp.isTeacher= $scope.writeUp.isTeacher? 1:0;
            if($scope.writeUp.allowAll === 1){
                $scope.writeUp.year = 'all';
                $scope.writeUp.classNum = 'all';
                $scope.writeUp.isTeacher = 1;
            }
			console.log($scope.writeUp);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/saveWriteUps',
			  data: $scope.writeUp

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#writeUpPopup').modal('hide') 
			    	AlertService.showAlert("Upload success!", "Succesfully uploaded the writeup");
			    	$scope.fetchWriteUps();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			    $scope.writeUp = {};
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}

		$scope.deleteWriteUps		= function(deleteItem){
			
			console.log(deleteItem);
			$http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/deleteWriteUps',
			  data: deleteItem

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#writeUpPopup').modal('hide'); 
			    	AlertService.showAlert("Campus Prime!", "Succesfully deleted the writeup");
			    	$scope.fetchWriteUps();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });

		}
        
        $scope.editWriteup          = function(){
            if($scope.writeUp.allowAll == 1){
                $scope.writeUp.year = 'all';
                $scope.writeUp.classNum = 'all';
                $scope.writeUp.isTeacher = 1;
            }
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/updateWriteUps',
			  data: $scope.writeUp

			}).then(function successCallback(response) {
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	$('#writeUpPopup').modal('hide') 
			    	AlertService.showAlert("Campus Prime!", "Succesfully updated the writeup");
			    	$scope.fetchWriteUps();
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			  }, function errorCallback(response) {
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });
        }
        $scope.updateWriteUps		= function(updateItem){
			
            $scope.writeUp = angular.copy(updateItem);
            $scope.editingItem = angular.copy(updateItem);
            $scope.isEditing = true;
			console.log(updateItem);
            
            $('#writeUpPopup').modal('show');
			

		}
        
        $('#writeUpPopup').on('hidden.bs.modal', function (e) {
            console.log('hidden event called');
            if($scope.isEditing){
                $scope.writeUp = {};
                $scope.editingItem = {};
                $scope.isEditing = false;
            }
            $scope.writeUp.file = {};
            $scope.$apply();
            
        });
        
		$scope.getClass = function(value){
            if(value === 'All' && $scope.filterTag.isApproved===1){
                return 'active';
            }
            
            return '';
        }
        
        $scope.doDisplay = function(item){
            if(item.publishedBy === UserService.getUserId()){
                return true;
            }
            return false;
        }
        
        $scope.updateClaps = function(clapCount, writeUp){
            console.log(clapCount + " " + writeUp.writeUpId);
            
            var updateClaps = {};
            updateClaps.count = clapCount;
            updateClaps.parentId = writeUp.writeUpId;
            updateClaps.userId = UserService.getUserId();
            var currentCount = angular.copy(writeUp.myClapCount);
            var urlPath = "updateClaps";
            if(currentCount ===0) urlPath = "saveClaps";
            console.log('***********************');
            console.log(urlPath);
            $http({
			  method: 'POST',
			  url: 'http://localhost:8080/RESTfulProject/REST/WebService/'+urlPath,
			  data: updateClaps

			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
			    console.log('In successCallback '+JSON.stringify(response));
			    if (response.data.status === Constants.success ) {
			    	writeUp.isCollapsed =true;
                    console.log(writeUp.clapCount + '+'+clapCount+'-' + currentCount);
                    writeUp.clapCount =  writeUp.clapCount + clapCount -currentCount;
			    	AlertService.showAlert("Upload success!", "Succesfully updated the clap count");
			    }
			    else{
			    	AlertService.showAlert("Upload Failed!", "Something wrong happened, Please try again later.");
			    }
			    
			    
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			    console.log('In errorCallback '+JSON.stringify(response));
			    
			  });
        }
        
        $scope.isImage = function(type){
            var imageArray = ['png','jpg','JPG','PNG','JPEG','jpeg'];
            if(imageArray.indexOf(type) >-1)
                return true;
            return false;
        }
        
        $('#writeUpPopup').on('show.bs.modal', function (e) {
            if(!$scope.isEditing){
                $scope.writeUp = {};       
                $scope.writeUp.file = {};                
                $scope.writeUp.allowAll = 1;
                $scope.$apply();
            }
            else{
                if($scope.writeUp.year === 'all'){
                    $scope.writeUp.allowAll = 1;
                }
                else{
                    $scope.writeUp.allowAll = 0;
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